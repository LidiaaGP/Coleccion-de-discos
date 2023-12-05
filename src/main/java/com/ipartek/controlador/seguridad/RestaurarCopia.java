package com.ipartek.controlador.seguridad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Discos;
import com.ipartek.modelo.dto.Grupos;

/**
 * Servlet implementation class RestaurarCopia
 */
@WebServlet("/RestaurarCopia")
public class RestaurarCopia extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurarCopia() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		File archivo=new File(DIRECCION_CS_GRUPOS);
		FileReader fr=new FileReader(archivo);
		BufferedReader br=new BufferedReader(fr);

		DB_Helper db=new DB_Helper();
		Connection con=db.Conectar();
		db.borrarBaseDatos(con);

		String linea="";
		while((linea=br.readLine())!=null) {

			//System.out.println(linea);
			String partes[]=linea.split(";");

			Grupos grupo=new Grupos(Integer.parseInt(partes[0]), partes[1]);

			db.restaurarFila(con,grupo);

		}
		archivo=new File(DIRECCION_CS_DISCOS);
		fr=new FileReader(archivo);
		br=new BufferedReader(fr);

		linea="";
		while ((linea=br.readLine()) != null) {
			//1;Somewhere in time;8;1
			String partes[]= linea.split(";");
			//"1" "Somewhere in time" "8" "1"
			// 0           1      2   3

			Discos disco = new Discos(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
			db.restaurarFilaDiscos(con, disco);

		}


		db.desconectar(con);

		request.getRequestDispatcher(JSP_SEGURIDAD).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
