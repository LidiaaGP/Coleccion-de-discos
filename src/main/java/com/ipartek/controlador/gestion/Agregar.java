package com.ipartek.controlador.gestion;

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
import com.ipartek.modelo.dto.Discos;
import com.ipartek.modelo.dto.Grupos;
import com.ipartek.modelo.dto.V_Discos;

/**
 * Servlet implementation class Agregar
 */
@WebServlet("/Agregar")
public class Agregar extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agregar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1
		  String titulo="";
		  if (request.getParameter("titulo")!=null) {
			  titulo=request.getParameter("titulo");
		  }
		  
		  int num_canciones=0;
		  if (request.getParameter("num_canciones")!=null) {
			  num_canciones=Integer.parseInt(request.getParameter("num_canciones"));
		  }
		  int grupo=0;
		  if(request.getParameter("grupo")!=null) {
			  grupo=Integer.parseInt(request.getParameter("grupo"));
		  }
		  
		  
		  Discos disco=new Discos(0, titulo, num_canciones, grupo);
		  
		  DB_Helper db=new DB_Helper();
		  Connection con=db.Conectar();
		  
		  
		  db.agregarDisco(con,disco);
		  
		  List<V_Discos> listaDiscos =  db.obtenerTodoslosDiscos(con);
		  List<Grupos> listaGrupos =  db.obtenerTodoslosGrupos(con);
		  
		  db.desconectar(con);
		  
		  request.setAttribute(ATR_LISTA_DISCOS, listaDiscos);
		  request.setAttribute(ATR_LISTA_GRUPOS, listaGrupos);
		  
		  request.getRequestDispatcher(JSP_GESTION).forward(request, response);
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
