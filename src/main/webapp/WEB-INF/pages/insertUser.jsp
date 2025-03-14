<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="net.etf.beans.UserBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dodaj novog korisnika</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<link href="styles/background-custom.css" type="text/css" rel="stylesheet">
<body>

	<div class="container mt-3 text-white">
		<h2 >Dodaj novog korisnika</h2>
		<form method="POST" action="?action=insert">
			<div class="form-group text-white">
				<label for="username">Korisnicko ime</label> <input type="text"
					class="form-control" name="username" id="username" required>
			</div>
			<div class="form-group text-white ">
				<label for="password ">Lozinka</label> <input type="password"
					class="form-control" name="password" id="password" required>
			</div>
			<div class="form-group text-white">
				<label for="firstName ">Ime</label> <input type="text"
					class="form-control" name="firstName" id="firstName" required>
			</div>
			<div class="form-group text-white">
				<label for="lastName ">Prezime</label> <input type="text"
					class="form-control" name="lastName" required>
			</div>
			<div class="form-group text-white">
				<label for="city" >Grad</label> <input type="text"
					class="form-control" name="city" required>
			</div>
			<div class="form-group text-white">
				<label for="email ">Email</label> <input type="email"
					class="form-control" name="email" required>
			</div>
			<div class="form-group text-white">
				<label for="role ">Uloga</label> <select class="form-control"
					name="role" required>
					<option value=""></option>
					<option value="1">Savjetnik</option>
					<option value="2">Korisnik</option>
				</select>
			</div>
			<div class="form-group text-white">
				<label for="status">Status</label> <select class="form-control"
					name="status" required>
					<option value="1">Aktivan</option>
					<option value="0">Neaktivan</option>
				</select>
			</div>
			<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
			<button type="submit" class="btn btn-primary">Dodaj
				korisnika</button>
		</form>
	</div>
</body>
</html>
