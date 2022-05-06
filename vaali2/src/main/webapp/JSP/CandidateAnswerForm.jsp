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
		<br>
	</form>

	<h1>Edit Answers</h1>




	<form method="get" action="../updateanswer">
		<ol>
			<c:forEach var="answers" items="${requestScope.answerlist}">
				<br>
				<input hidden="hidden" readonly value="${answers.candidate_id}"
					name="candidate_id" />
				<input hidden="hidden" readonly value="${answers.answer_id}"
					name="answer_id" />
				<br>${answers.questions.question} <input readonly hidden="hidden" value="${answers.questions.question}"
					name="question_id" />
				<br>Answer: <input value="${answers.answer_int}"
					name="answer_int" />
				<br>
				<textarea rows="3" cols="55" name="answer">${answers.answer_string}</textarea>
			</c:forEach>
		</ol>
		<br>
		<button type="submit" class="button">Update</button>
	</form>



</body>
</html>