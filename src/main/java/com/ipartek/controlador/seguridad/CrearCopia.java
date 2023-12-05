package com.ipartek.controlador.seguridad;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Discos;
import com.ipartek.modelo.dto.Grupos;


@WebServlet("/CrearCopia")
public class CrearCopia extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearCopia() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//obtener todos los grupos de musica en una lista
		//select * from grupos y meterlo en una lista

		//obtener una copia de los discos desde las tablas
		//select * from discos y meterlo en una lista

		DB_Helper db=new DB_Helper();
		Connection con=db.Conectar();

		List<Grupos> listaGrupos =  db.obtenerTodoslosGrupos(con);
		List<Discos> listaDiscos =  db.obtenerDiscos(con);
		
		db.desconectar(con);



		//crear un fichero de grupos llamados grupos.csv en c:\lidia\grupos.csv

		//la lista de grupos la convertimos cada fila a un string con formato csv

		//voy a meter ese string en el fichero

		//cerrar el fichero

		try {
			FileWriter archivo=new FileWriter(DIRECCION_CS_GRUPOS); 

			PrintWriter pw=new PrintWriter(archivo);

			for (Grupos elemento : listaGrupos) {
				pw.println(elemento);
			}
			archivo.close();
			System.out.println("GUARDADO");
			
			archivo=new FileWriter(DIRECCION_CS_DISCOS);
			pw=new PrintWriter(archivo);
			for (Discos disco : listaDiscos) {
				pw.println(disco);
			}
			archivo.close();
			System.out.println("GUARDADO");
			
		} catch (IOException e) {
			System.out.println("NO SE PUDO GUARDAR");
			e.printStackTrace();
		}


		//crear un fichero de grupos llamados discos.csv en c:\lidia\discos.csv


		request.setAttribute(ATR_LISTA_GRUPOS, listaGrupos);
		request.setAttribute(ATR_LISTA_DISCOS, listaDiscos);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
