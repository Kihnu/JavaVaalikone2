<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
 <h1>update questions</h1>
<body>
<form  action='../updatequestion' method='post' >
<input type="text" name="id" value="${requestScope.questions.question_id}" readonly/> <br>
<textarea rows="3" cols="55" name="question">${requestScope.questions.question}</textarea>
<input type='submit' name='ok' value='OK'>
</form>

</body>
</html>