<%@page import="net.etf.beans.UserBean"%>
<%@page import="net.etf.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="net.etf.beans.UserBean"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Korisnici</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="styles/background-custom.css" type="text/css" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Fitnes centar</a>
   
    <div class=" navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="#">Korisnici</a></li>
            <li class="nav-item"><a class="nav-link" href="?action=categories">Kategorije</a></li>
            <li class="nav-item"><a class="nav-link" href="?action=statistic">Statistika</a></li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="?action=logout">Odjava</a></li>
        </ul>
    </div>
</nav>





	<div class="container mt-3 text-white ">
<h2 >Korisnici</h2>
		<div class="table-responsive " >
		<table class="table text-white" style="overflow-x: auto;">
			<thead>
				<tr>
					<th>ID</th>
					<th>Korisničko ime</th>
					<th>Ime</th>
					<th>Prezime</th>
					<th>Grad</th>
					<th>Email</th>
					<th>Uloga</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (User c : userBean.findAll()) {
				%>
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getUsername()%></td>
					<td><%=c.getFirstName()%></td>
					<td><%=c.getLastName()%></td>
					<td><%=c.getCity()%></td>
					<td><%=c.getEmail()%></td>
					<td>
						<%
						if (c.getRole() == 0) {
							out.print("admin");
						} else if (c.getRole() == 1) {
							out.print("savjetnik");
						} else if (c.getRole() == 2) {
							out.print("korisnik");
						} else {
							out.print("nepoznato");
						}
						%>
					</td>
					<td>
						<div style="display: flex; align-items: center;">
							<form id="changeStatusForm<%=c.getId()%>" method="post"
								action="?action=<%=c.isStatus() ? "blockUser" : "unblockUser"%>">
								<button type="submit" class="btn btn-link"
									data-bs-toggle="tooltip" data-bs-placement="top"
									data-bs-title="<%=c.isStatus() ? "Aktivan" : "Blokiran"%>">
									<%=c.isStatus() ? "Aktivan" : "Blokiran"%>
								</button>
								<input type="hidden" name="id" value="<%=c.getId()%>">
							</form>

							<form id="deleteUserForm" action="?action=deleteUser"
								method="post">
								<input type="hidden" id="deleteUser" name="deleteUser"
									value="<%=c.getId()%>">
								<button type="submit" name="submit" class="btn btn-link">
									
									<img src="img/del.png" alt="Obrisi" width="20" height="20">
								</button>
		
							</form>
							<form id="updeteUserForm" action="?action=updateUser" method="post">	 
									<input type="hidden" id="updateUserId" name="updateUserId"
									value="<%=c.getId()%>">
									<button type="submit" name="submit" class="btn btn-link">
									<img src="img/edi.png" alt="Uredi" width="20" height="20">
						
								</button>
							</form>		
							
						</div>
					</td>


				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		
		<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
		</div>
	</div>
	<div class="text-center">
		<a href="?action=insertUser" class="btn btn-primary">Dodaj
			korisnika</a>
	</div>
</body>
</html>
