<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seguridad</title>
<link rel="stylesheet" href="styles/styles.css"></link>
</head>
<body>
	<%@ include file="includes/cabecera.jsp"%>
	<main>
		<h2>Seguridad</h2>
		<form method="get" action="CrearCopia">
			<input type="submit" value="Crear copia de seguridad">
		</form>
		<form method="get" action="RestaurarCopia">
			<input type="submit" value="Restaurar copia de seguridad">
		</form>
	</main>
	<%@ include file="includes/pie.jsp"%>
</body>
</html>