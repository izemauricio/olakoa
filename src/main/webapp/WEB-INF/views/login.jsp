<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/list.css" />"
	type="text/css" />

<script src="<c:url value="/resources/login.js" />"></script>
<title>Login</title>
</head>
<body onload="processQuery()">
	<div class="container" style="width: 300px;">
		<form class="form-signin" action="login" method="POST">
			<h2 style="margin-top: 200px;" class="form-signin-heading">Please
				sign in</h2>
			<div id="msg1" style="display: none; margin-top: 5px; margin-bottom: 10px;">Incorrect username or
				password. Please try again.</div>
			<div id="msg2" style="display: none; margin-top: 5px; margin-bottom: 10px;">Logout done.</div>
			<label class="sr-only">Username</label> <input name="username"
				type="text" id="inputEmail" class="form-control"
				placeholder="Username" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" class="form-control"
				placeholder="Password" required name="password">
			<button style="margin-top: 10px;"
				class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

