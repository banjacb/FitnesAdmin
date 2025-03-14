<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="attributeBean" class="net.etf.beans.AttributeBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Izmjena atributa</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="styles/background-custom.css" type="text/css" rel="stylesheet">
</head>

<body>
	<div class="container mt-3 text-white">
		<h2>Izmjena atributa</h2>
		<form method="post" action="?action=editAttribute">
			<div class="form-group text-white">
				<label for="nameAttributeNew">Novo ime atributa koje mijenjate</label> <input
					type="text" class="form-control" name="nameAttributeNew" id="nameAttributeNew"
					value="<%=session.getAttribute("attributeIdUpdateName")%>">
			</div>
			<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
			<button type="submit" class="btn btn-primary">Sacuvaj promjene</button>
		</form>
	</div>
</body>
</html>
