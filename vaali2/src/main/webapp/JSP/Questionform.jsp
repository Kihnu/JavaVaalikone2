
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Questionsedit.css>



<title>Edit the questions!</title>



<script>

function validateForm(f) {
  if (f.value != "") {
     alert("New question added!");
  }
</script>


</head>

<form method="get" action="/AdminMain">
	<button type="submit" class="exitbutton">Back to Admin page</button>
</form>

<br>
<br>
<br>
<h1>Edit the questions!</h1>
<body>

	<br>

	<div>

		<form action='../addquestion' name="form" class="form" method='post'onSubmit="validateForm(form.OK)">
			<h2>Add question</h2>
			<textarea rows="3" cols="100" name="question" required></textarea>

			<br> <br> <input type='submit' name = "OK" class="ok" name='ok'
				value='OK' >

			</form>
		</div>

		<ol>
		<c:forEach var="questions" items="${requestScope.questionlist}">
			<br>
		${questions.question_id}. ${questions.question} 

	<br>


			<a href='../deletequestion?id=${questions.question_id}'
				onclick="return confirm('Are you sure you want to delete the question?')">Delete</a>


	<!--  Suoraan restiin, /rest/questionservice/deletequestion/${questions.question_id} -->
	<!--  Pitää olla @GET metodi -->
	<!--  <a href='../deletequestion?id=${questions.question_id}'onclick="return confirm('Are you sure you want to delete the question?')"  >Delete</a>--> 
	
	



			<a href='../readtoupdatequestion?id=${questions.question_id}'>Update</a>
			<br>
			<br>

			<hr class="solid">

		</c:forEach>
	</ol>
</body>
</html>