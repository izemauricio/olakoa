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
				style="margin-top: 20px; margin-bottom: 10px; font-size: 40px;">User</div>
		</div>

		<div class="row" style="margin-top: 10px; margin-bottom: 0px;">
			<div class="col-md-12"
				style="margin-top: 0px; margin-bottom: 10px; font-size: 25px;">Create
				Beverage</div>
		</div>

		<div class="input-group">
			<form action="<%=request.getContextPath()%>/user/drinks/update" method="POST" class="well">
				<input type="hidden" name="idd" value="${thedrink.id}">
				<div class="row">
					<div class="col-md-12">Name</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 10px;">
						<input type="text" name="name" style="width: 400px;" required
							value="${thedrink.name}">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">Description</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 10px;">
						<input type="text" name="description" style="width: 400px;"
							required value="${thedrink.description}">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">Thumbnail</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 10px;">
						<input type="url" name="thumbnail" style="width: 400px;" required
							value="${thedrink.thumbnail}">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">Unit Price</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 10px;">
						<input type="number" name="cost" style="width: 400px;" required
							value="${thedrink.cost}">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="checkbox" style="margin-bottom: 10px;">
							<label style="margin-bottom: 10px;"> <c:if
									test="${thedrink.posted == 'true'}">
									<input name="posted" type="checkbox" checked>
								</c:if> <c:if test="${thedrink.posted == 'false'}">
									<input name="posted" type="checkbox">
								</c:if> Posted
							</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-2">
						<input class="btn btn-sm btn-danger" type="button" value="Cancel"
							onclick="history.go(-1);">
					</div>
					<div class="col-md-2">
						<input class="btn btn-sm btn-primary" type="submit" value="Update">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>