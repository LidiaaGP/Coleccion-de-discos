<%@page import="com.ipartek.modelo.dto.Grupos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.ipartek.modelo.dto.V_Discos"%>
<%@page import="com.ipartek.modelo.DAO_Constantes"%>

<%
List<Grupos> listaGrupos = new ArrayList<Grupos>();
if(request.getAttribute(DAO_Constantes.ATR_LISTA_GRUPOS)!=null){
 listaGrupos = (List<Grupos>)request.getAttribute(DAO_Constantes.ATR_LISTA_GRUPOS);
}


V_Discos disco = new V_Discos();
if(request.getAttribute(DAO_Constantes.ATR_DISCO)!=null){
 disco=(V_Discos)request.getAttribute(DAO_Constantes.ATR_DISCO);
}
%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar</title>
<link rel="stylesheet" href="styles/styles.css"></link>
</head>
<body>
	<%@ include file="includes/cabecera.jsp"%>
	<main>
		<h2>Modificacion de Discos</h2>
		<br>

		<section>
			<form method="GET" action="Modificar">
				<h3>Modificar disco</h3>
				<input type="hidden" name="id"  required value="<%=disco.getId() %>"><br>
				<input type="text" name="titulo" placeholder="Titulo" required value="<%=disco.getTitulo() %>"><br>
				<input type="number" name="num_canciones" placeholder="Numero de canciones" required value="<%=disco.getNum_canciones() %>"><br>
				
				<select name="grupo" required>
				    <%for(Grupos elemento:listaGrupos){
    
				     if(elemento.getId()==disco.getFk_grupo()){
				      %>
				      <option value="<%=elemento.getId()%>" selected><%=elemento.getGrupo()%></option>
				     <% 
				     }else{
				     %>
				      <option value="<%= elemento.getId()%>"><%= elemento.getGrupo()%></option>
				     <% 
				     }  
				    } %> 

				</select>

				<br> <input type="submit" value="Modificar">
			</form>
		</section>
	</main>
	<%@ include file="includes/pie.jsp"%>

</body>
</html>