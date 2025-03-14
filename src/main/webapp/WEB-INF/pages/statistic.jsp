
<%@page import="net.etf.dto.Statistic"%>
<%@page import="net.etf.beans.StatisticBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="statisticsBean" class="net.etf.beans.StatisticBean"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Statistika</title>

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
				<li class="nav-item"><a class="nav-link" href="?action=users">Korisnici</a>	</li>
				<li class="nav-item"><a class="nav-link" href="?action=categories">Kategorije</a></li>
				<li class="nav-item"><a class="nav-link"
					href="# active">Statistika</a></li>
			
			</ul>
			<ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="?action=logout">Odjava</a></li>
        </ul>
		</div>
	</nav>

<div class="container mt-3 text-white">
    <h2>Statistika</h2>
    <table class="table text-white">
        <thead>
            <tr>
           
                <th>Vrijeme</th>
                <th>Opis</th>
            </tr>
        </thead>
        <tbody>
            <% for (Statistic stat : statisticsBean.findAll()) { %>
                <tr>
                  
                    <td><%= stat.getTime() %></td>
                    <td><%= stat.getDescription() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
