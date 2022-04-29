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
<form  action='../readtoupdatequestion' method='post' >
<input type='text' name='id' value='${requestScope.questions.question_id}'>
<input type='text' name='question' value='${requestScope.questions.question}'>
<input type='submit' name='ok' value='OK'>
</form>

</body>
</html>