<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Allcandidates.css>
<title>Add candidate</title>
</head>
<body>

	<div class="formi">
		<form action="/AddCandidate" method="post">
			<label>First name: </label> <input type="text" name="firstname" value=""/><br> 
			<label>Surname*: </label> <input type="text" name="surname" value="" required/><br> 
			<label>Voting number*: </label> <input type="number" name="vote" value="" required/><br> 
			<label>Age*: </label> <input type="number" name="age" value="" required/><br> 
			<label>Party: </label> <input type="text" name="party" value=""/><br> 
			<label>Profession: </label> <input type="text" name="pro" value="" /><br> 
			<label style="transform: translateY(-25%);">Why are you running? </label>
			<textarea rows="3" cols="55" name="why">${requestScope.candidate.why}</textarea>
			<br> <br> 
			<label>What do you want when elected? </label>
			<textarea rows="3" cols="55" name="what">${requestScope.candidate.what}</textarea>
			<br> <br>
			* = required
			<button type="submit" class="button">Add</button>
		</form>
	</div>

</body>
</html>