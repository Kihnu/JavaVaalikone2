<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../CSS/index.css">

<title>Admin Login</title>

</head>

<body>
	<h1 style="text-align: center">Admin Login</h1>

	<form action="/AdminLogin" method="post">
		<p>Username:</p>

		<input type="text" name="username" />
		<p>Password:</p>

		<input type="password" name="password" /><br> <br> <input
			type="submit" value="Login" class="button2" style="float:none; height: 50px"/>
	</form>

</body>

</html>