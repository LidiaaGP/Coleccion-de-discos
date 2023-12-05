package com.ipartek.controlador.borrar;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Grupos;
import com.ipartek.modelo.dto.V_Discos;

/**
 * Servlet implementation class Borrar
 */
@WebServlet("/Borrar")
public class Borrar extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
    public Borrar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1: obtener parametros y atributos
		
		//2: maquetacion de la informacion
				
		int id=0;
		if(request.getParameter("id")!=null) {
			id=Integer.parseInt(request.getParameter("id"));
		}


		DB_Helper db=new DB_Helper();
		Connection con=db.Conectar();
		db.borrardisco(con, id);
		
		List<V_Discos> listaDiscos =  db.obtenerTodoslosDiscos(con);		
		List<Grupos> listaGrupos =  db.obtenerTodoslosGrupos(con);
		
		//4: operaciones con la BD
				
		//5: DESCONEXION DE LA BD
		db.desconectar(con);
		
				
		//6: MOCHILA
		request.setAttribute(ATR_LISTA_DISCOS, listaDiscos);
		request.setAttribute(ATR_LISTA_GRUPOS, listaGrupos);
		
		//7: viaje  
		request.getRequestDispatcher(JSP_GESTION).forward(request, response);
				
				
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
