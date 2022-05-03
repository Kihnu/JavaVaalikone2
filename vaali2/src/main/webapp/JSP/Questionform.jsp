
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Questionsedit.css>



<title>Edit the questions!</title>
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

<form action='../addquestion' class="form" method='post'>
<h2> Add question </h2>
<textarea rows="3" cols="55" name="question" required></textarea>

<br>
<br>
<input type='submit' class= "ok" name='ok' value='OK' onclick="return alert('New question added!')" >

</form>
	</div>

<ol>
<c:forEach var="questions" items="${requestScope.questionlist}">


<br>
<br>
	${questions.question_id}. ${questions.question} 

	<br>
	
	


	

	
	<a href='../deletequestion?id=${questions.question_id}'onclick="return confirm('Are you sure you want to delete the question?')"  >Delete</a> 
	
	
	<a href='../readtoupdatequestion?id=${questions.question_id}'>Update</a>
		<br>

</c:forEach>
</ol>
</body>
</html>