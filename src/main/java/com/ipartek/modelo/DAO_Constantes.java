package com.ipartek.modelo;

public interface DAO_Constantes {
	
	
	//Constantes de
	String NOMBRE_BD = "bd_discos";
	String RUTA = "jdbc:mysql://localhost:3306/" + NOMBRE_BD;
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String USUARIO = "root";
	String PASS = "1234";
	
	
	//Procedimientos almacenados
	String SP_OBTENER_DISCOS="call sp_obtener_todos_discos();";
	String SP_DISCOS="call sp_obtener_discos();";
	String SP_OBTENER_GRUPOS="call sp_obtener_todos_grupos();";
	String SP_INSERTAR_DISCO="call sp_insertar_disco(?, ?, ?);";
	String SP_BORRAR_DISCO="call sp_borrar_disco(?);";
	String SP_MODIFICAR_DISCO="call sp_modificar_disco(?,?,?,?);";
	String SP_OBTENER_DISCO_POR_ID="call sp_obtener_disco_por_id(?)";
	String SP_RESTAURAR_GRUPO="call bd_discos.sp_restaurar_una_fila_grupos(?,?)";
	String SP_RESTAURAR_DISCO="call bd_discos.sp_restaurar_una_fila_discos(?,?,?,?)";
	String SP_BORRAR_BD="call bd_discos.sp_borrar_todo()";
	
	
	String JSP_GESTION="gestion.jsp";
	String JSP_MODIFICAR="modificar.jsp";
	String JSP_VER_TODOS="mostrar_todos.jsp";
	String JSP_SEGURIDAD="seguridad.jsp";
	
	String ATR_LISTA_DISCOS="atr_lista_discos";
	String ATR_LISTA_GRUPOS="atr_lista_grupos";
	String ATR_DISCO="atr_disco";
	
	
	String TABLA_GRUPOS="grupos";
	String GRUPOS_ID="id";
	String GRUPOS_GRUPO="grupo";
	
	String TABLA_DISCOS="discos";
	String DISCOS_ID="id";
	String DISCOS_TITULO="titulo";
	String DISCOS_NUM_CANCIONES="num_canciones";
	//String DISCOS_GRUPO="grupo";
	String DISCOS_FK_GRUPO="fk_grupo";
	
	
	String V_TABLA_DISCOS="discos_con_grupos";
	String V_DISCOS_ID="id";
	String V_DISCOS_TITULO="titulo";
	String V_DISCOS_NUM_CANCIONES="num_canciones";
	String V_DISCOS_GRUPO="grupo";
	String V_DISCOS_FK_GRUPO="fk_grupo";
	
	String DIRECCION_CS_GRUPOS="C:\\CSV\\grupos.csv";
	String DIRECCION_CS_DISCOS="C:\\CSV\\discos.csv";
}
