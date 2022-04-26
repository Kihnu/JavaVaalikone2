<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Allcandidates.css>
<title>Edit</title>
</head>
<body>
	<form method="get" action="/AdminCandidates">
		<button type="submit" class=exitbutton>Back</button>
	</form>
	<br>
	<div class="allcandidates">
		<h1>Update information</h1>
		<div class="formi">
			<form action="/UpdateCandidate" method="post">
				<input hidden=hidden type="text" name="id"
					value="${requestScope.candidate.id}" readonly /> <br> <label>First
					name: </label> <input type="text" name="firstname"
					value="${requestScope.candidate.firstname}" /><br> <label>Surname:
				</label> <input type="text" name="surname"
					value="${requestScope.candidate.surname}" /><br> <label>Voting
					number: </label> <input type="number" name="vote"
					value="${requestScope.candidate.vote_nro}" /><br> <label>Age:
				</label> <input type="number" name="age"
					value="${requestScope.candidate.age}" /><br> <label>Party:
				</label> <input type="text" name="party"
					value="${requestScope.candidate.party}" /><br> <label>Profession:
				</label> <input type="text" name="pro"
					value="${requestScope.candidate.profession}" /><br> <label
					style="transform: translateY(-25%);">Why are you running? </label>
				<textarea rows="3" cols="55" name="why">${requestScope.candidate.why}</textarea>
				<br> <br> <label>What do you want when elected? </label>
				<textarea rows="3" cols="55" name="what">${requestScope.candidate.what}</textarea>
				<br>
				<button type="submit" class="button">Edit</button>
			</form>

			<form action="/DeleteCandidate" method="post">
				<input hidden=hidden name="id" value="${requestScope.candidate.id}"
					readonly />
				<button type="submit" class="button"
					onclick="return confirm('Are you sure you want to delete this candidate?');">Delete
					candidate</button>
				<br> <br> <br>
			</form>
		</div>
		<br> <br>

	</div>



</body>
</html>