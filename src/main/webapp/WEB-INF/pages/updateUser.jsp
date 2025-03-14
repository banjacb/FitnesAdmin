<%@page import="net.etf.dto.User"%>
<%@page import="net.etf.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" class="net.etf.beans.UserBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Izmjena korisnickih podataka</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="styles/background-custom.css" type="text/css" rel="stylesheet">
</head>
<%
User user;
String selectedId = request.getParameter("updateUserId");

if(selectedId != null )
{
	Integer id=Integer.parseInt(selectedId);
	user = userBean.getUserById(id);
%>

<body>

<div class="container mt-3 text-white">

<form method="POST" action="?action=updateUserSave&id=<%= user.getId() %>">
    <div class="form-group text-white">
        <label for="username">Korisnicko ime</label>
        <input type="text" class="form-control" name="username" id="username" value="<%= user.getUsername() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="password">Lozinka</label>
        <input type="password" class="form-control" name="password" id="password" value="<%= user.getPassword() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="firstName">Ime</label>
        <input type="text" class="form-control" name="firstName" id="firstName" value="<%= user.getFirstName() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="lastName">Prezime</label>
        <input type="text" class="form-control" name="lastName"  id="lastName" value="<%= user.getLastName() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="city">Grad</label>
        <input type="text" class="form-control" name="city" id="city" value="<%= user.getCity() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="email">Email</label>
        <input type="email" class="form-control" name="email" id="email" value="<%= user.getEmail() %>" required>
    </div>
    <div class="form-group text-white">
        <label for="role">Uloga</label>
        <select class="form-control" name="role"  id="role" required>
            <option value="1" <%= user.getRole() == 1 ? "selected" : "" %>>Savjetnik</option>
            <option value="2" <%= user.getRole() == 2 ? "selected" : "" %>>Korisnik</option>
        </select>
    </div>
    <div class="form-group text-white">
        <label for="status">Status</label>
        <select class="form-control" name="status" id="status" required>
            <option value="1" value="1" <%= user.isStatus() == true ? "selected" : "" %> >Aktivan</option>
            <option value="0"  value="0" <%= user.isStatus() == false ? "selected" : "" %>>Neaktivan</option>
        </select>
    </div>
    <p class="p"><%= session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : "" %></p>
    <button type="submit" class="btn btn-primary" name="submit">Sacuvaj izmjene</button>

<%}
	%>
  
   
</form>
</div>
</body>
</html>