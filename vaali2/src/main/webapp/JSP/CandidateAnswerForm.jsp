<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Questionsedit.css>
<title>Edit the Candidates Answers here!</title>
</head>

<body>
	<!--  Button to admin main -->
	<form method="get" action="/AdminCandidates">
		<button type="submit" class="exitbutton">Back to Admin page</button>
	</form>

	<h1>Edit Answers</h1>

	<ol>
		<c:forEach var="answers" items="${requestScope.answerslist}">
			<br>
		${AnswersC.candidate_id}. ${AnswersC.question_id} ${AnswersC.answer_int} 
		</c:forEach>
	</ol>
</body>
</html>