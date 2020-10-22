<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>
<%@page import="com.mauricio.olakoa.drinks.Drink"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/list.css" />"
	type="text/css" />
<title>Home</title>
</head>

<body>
	<div class="container">
		<nav class="navbar navbar-default" style="margin-top: 10px;">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Olakoa</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">


					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Welcome ${user.username}</a></li>
						<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-12"
				style="margin-top: 20px; margin-bottom: 10px; font-size: 40px;">Home</div>
		</div>

		<div class="row" style="margin-top: 10px; margin-bottom: 0px;">
			<div class="col-md-12"
				style="margin-top: 0px; margin-bottom: 10px; font-size: 25px;">Drinks</div>
		</div>

		<div class="row">
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Thumbnail</th>
					<th>Description</th>
					<th>Unit Cost</th>
				</tr>

				<c:forEach items="${drinks}" var="drink">
					<tr>
						<td><a href="drinks/update/${drink.id}">${drink.name}</a></td>
						<td><img src="${drink.thumbnail}" class="img-thumbnail"
							style="width: 100px; height: 100px;"></td>
						<td>${drink.description}</td>
						<td>$<c:out value="${drink.cost/100}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>