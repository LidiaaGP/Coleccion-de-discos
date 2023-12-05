<%@page import="com.ipartek.modelo.dto.V_Discos"%>
<%@page import="com.ipartek.modelo.DAO_Constantes"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
List<V_Discos> lista = new ArrayList<V_Discos>();

if (request.getAttribute(DAO_Constantes.ATR_LISTA_DISCOS) != null) {
	lista = (List<V_Discos>) request.getAttribute(DAO_Constantes.ATR_LISTA_DISCOS);
}

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Discos</title>
		<link rel="stylesheet" href="styles/styles.css"></link>
		<link rel="stylesheet" href="styles/styles_index.css"></link>
	</head>
	
	<body>
	<%@ include file="includes/cabecera.jsp" %>
	<main>
		<h2>Ver todos los discos</h2>
		<br>
		<%
		 for(V_Discos elemento:lista){
		%>
		<section>
			<h3><%= elemento.getTitulo() %></h3>
			<p><%= elemento.getNum_canciones() %></p>
			<p><%= elemento.getGrupo() %></p>
		</section>
		 <%   
		   }
		 %>
	</main>
	<%@ include file="includes/pie.jsp" %>
	</body>
</html>