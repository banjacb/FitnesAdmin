<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.etf.beans.UserBean"%>
<jsp:useBean id="userBean" class="net.etf.beans.UserBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pocetna strana</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="styles/style.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h1 class="text-center">Fitnes centar</h1>
                </div>
                <div class="card-body">
                   <form method="post" action="?action=login">
                        <div class="form-group">
								<label for="username">Korisnicko ime:</label>
								 <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Lozinka:</label>
                            <input type="password" class="form-control" id="password" name="password" >
                            <p class="p"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Prijavi se</button>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>