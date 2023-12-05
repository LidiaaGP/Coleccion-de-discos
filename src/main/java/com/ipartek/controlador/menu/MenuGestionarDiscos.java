package com.ipartek.controlador.menu;

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
 * Servlet implementation class MenuGestionarDiscos
 */
@WebServlet("/MenuGestionarDiscos")
public class MenuGestionarDiscos extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuGestionarDiscos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB_Helper db=new DB_Helper();
		Connection con=db.Conectar();
		
		 
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
