<%@page import="net.etf.beans.CategoryBean"%>
<%@page import="net.etf.dto.Category"%>
<%@page import="net.etf.dto.Attribute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="categoryBean" class="net.etf.beans.CategoryBean"
	scope="session"></jsp:useBean>
<jsp:useBean id="attributeBean" class="net.etf.beans.AttributeBean"
	scope="session"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Kategorije</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="styles/background-custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand text-white" href="#">Fitnes centar</a>
		
		<div class=" navbar-collapse" id="navbarNav">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="?action=users">Korisnici</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Kategorije</a></li>
				<li class="nav-item"><a class="nav-link" href="?action=statistic">Statistika</a></li>           
        </ul>
		
		<ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="?action=logout">Odjava</a></li>
        </ul>
		</div>
	</nav>

	<div class="container mt-3 text-white " >
		<h2>Kategorije</h2>
		<form method="post" action="?action=addCategory">
			<div class="form-group">
				<label for="categoryName">Ime kategorije:</label> <input
					type="text" class="form-control" id="categoryName"
					name="categoryName">
			</div>
			<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
			<button type="submit" class="btn btn-primary">Dodaj	kategoriju</button>
		</form>

		<div class="table-responsive mt-3 text-white">
			<table class="table ">
				<thead class="thead-plava text-white">
					<tr>
						<th>ID</th>
						<th>Ime kategorije</th>
						<th>Atributi</th>
						<th>Akcije</th>
					</tr>
				</thead>
				<tbody class="text-white">
					<%
					for (Category category : categoryBean.findAll()) {
					%>
					<tr>
						<td><%=category.getId()%></td>
						<td>

							<div
								style="display: flex; align-items: center; justify-content: flex-end;">
								<%=category.getName()%>

								<form method="post" action="?action=updateCategory"
									style="margin-left: 10px;">
									<input type="hidden" name="categoryIdInput"
										value="<%=category.getId()%>">
									<button type="submit" name="submitUpdate" class="btn btn-link">
										<img src="img/edi.png" alt="Izmjeni" width="20" height="20">
									</button>
								</form>

								<form method="post" action="?action=deleteCategory">
									<input type="hidden" name="categoryId"
										value="<%=category.getId()%>">
									<button type="submit" name="submitDeleteCategory"
										class="btn btn-link">
										<img src="img/del.png" alt="Obrisi" width="20" height="20">
									</button>
								</form>
							</div>

						</td>
						<td>
							<ul class="attribute-list">
								<%
								for (Attribute attribute : attributeBean.findAll(category.getId())) {
								%>
								<li style="display: flex; align-items: center;"><span
									style="margin-right: auto;"><%=attribute.getName()%></span>


									<form method="post" action="?action=deleteAttribute">
										<input type="hidden" name="attributeIdDelete"
											value="<%=attribute.getId()%>">
										<button type="submit" name="submitDelete" class="btn btn-link">
											<img src="img/del.png" alt="Obriši" width="20" height="20">
										</button>
									</form>


									<form method="post" action="?action=updateAttribute">
										<input type="hidden" name="attributeIdInput"
											value="<%=attribute.getId()%>">
										<button type="submit" name="submitUpdate" class="btn btn-link">
											<img src="img/edi.png" alt="Izmjeni" width="20" height="20">
										</button>
									</form></li>
								<%
								}
								%>
							</ul>

						</td>
						<td>
							<form method="post" action="?action=addAttribute">
								<input type="hidden" name="categoryId"
									value="<%=category.getId()%>"> <input type="text"
									name="nameAttribute" id="nameAttribute" required="required">
								<button type="submit" name="submitAddAttribute"
									class="btn btn-link">Dodaj atribut</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
