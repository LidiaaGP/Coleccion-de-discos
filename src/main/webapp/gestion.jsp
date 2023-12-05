<%@page import="com.ipartek.modelo.dto.Grupos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.ipartek.modelo.dto.V_Discos"%>
<%@page import="com.ipartek.modelo.DAO_Constantes"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
List<V_Discos> lista_disco = new ArrayList<V_Discos>();

if (request.getAttribute(DAO_Constantes.ATR_LISTA_DISCOS) != null) {
	lista_disco = (List<V_Discos>) request.getAttribute(DAO_Constantes.ATR_LISTA_DISCOS);
}

%>
<%
List<Grupos> lista_grupo = new ArrayList<Grupos>();

if (request.getAttribute(DAO_Constantes.ATR_LISTA_GRUPOS) != null) {
	lista_grupo = (List<Grupos>) request.getAttribute(DAO_Constantes.ATR_LISTA_GRUPOS);
}

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion</title>
<link rel="stylesheet" href="styles/styles.css"></link>
</head>

<body>
	<%@ include file="includes/cabecera.jsp"%>
	<main>
		<h2>Gestion de Discos</h2>
		<br>
		<section>
			<form method="POST" action="Agregar">
				<h3>Agregar Discos</h3>
				<input type="text" name="titulo" placeholder="Titulo"><br>
				<input type="number" name="num_canciones"
					placeholder="Numero de canciones" required><br> <select
					name="grupo">
					<%
					 for(Grupos elemento:lista_grupo){
					%>
					<option value="<%= elemento.getId()%>">
						<%= elemento.getGrupo() %></option>
					<%   
					   }
					 %>
				</select><br> <input type="submit" value="Agregar">
			</form>
		</section>
		<br>
		<section>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Título</th>
						<th>Número de Canciones</th>
						<th>Grupo</th>
						<th>Opciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					 for(V_Discos elemento:lista_disco){
					%>
					<tr>
						<td><%= elemento.getId() %></td>
						<td><%= elemento.getTitulo() %></td>
						<td><%= elemento.getNum_canciones() %></td>
						<td><%= elemento.getGrupo() %></td>
						<td><a href="Borrar?id=<%= elemento.getId() %>">Borrar</a> |
							<a href="FormModificar?id=<%= elemento.getId() %>">Modificar</a>
					</tr>
					<%   
					   }
					 %>
				</tbody>
			</table>
		</section>
	</main>
	<%@ include file="includes/pie.jsp"%>
</body>
</html>