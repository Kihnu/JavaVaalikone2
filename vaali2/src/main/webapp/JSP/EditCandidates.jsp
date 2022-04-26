<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Candidates"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Allcandidates.css>
<title>Edit Candidates</title>
</head>
<body>

	<form action="http://localhost:8080/AdminMain?">
		<button type="submit" class=exitbutton>To Admin Page</button>
	</form>

	<form action="../jsp/AddCandidateJSP.jsp">
		<button type="submit" class=addbutton>Add candidate</button>
	</form>
	<br>

	<h1>Edit Candidates</h1>

	<c:forEach var="candidates" items="${requestScope.candidates}">
		<div class="allCandidatesEdit">
			<div class="id">${candidates.id}</div>
			<div class="firstname">${candidates.firstname}</div>
			<div class="surname">${candidates.surname}</div>
			<br>
			<div class="age">Age: ${candidates.age}</div>
			<br>
			<div class="party">Party: ${candidates.party}</div>
			<br>
			<div class="profession">Profession: ${candidates.profession}</div>
			<br>
			<div class="number">Voting number: ${candidates.vote_nro}</div>
			<!--<form action="/EditCandidate?id=${candidates.id}">
				<button type="submit" class="infobutton">Edit Candidate</button>  -->
			<a href="/EditCandidate?id=${candidates.id}" class="infobutton">Edit
				candidate</a> <br>
			<!-- </form>
			 
			
			-->
			<hr class="solid">
		</div>

	</c:forEach>
</body>
</html>