<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Questionsedit.css>
<title>Insert title here</title>


</head>
<form method="get" action="/readquestion">
		<button type="submit" class="exitbutton">Back</button>
	</form>

<br>
<br>
 <h1>Update Question:</h1>
<body>
<form  class= "form" action='../updatequestion' method='post' >
<input hidden="hidden" value="${requestScope.questions.question_id}" name="id"/><br>
<br>
<textarea rows="3" cols="55" name="question" required>${requestScope.questions.question} </textarea>
<br>
<br>
<input type='submit' class= "ok" name='ok' value='OK'>


</form>

</body>
</html>