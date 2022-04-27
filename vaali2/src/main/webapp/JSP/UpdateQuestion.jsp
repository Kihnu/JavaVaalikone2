<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action='../updatequestion' method='post'>
<input type='text' name='id' value='${requestScope.question.id }'>
<input type='text' name='breed' value='${requestScope.questions.breed }'>
<input type='submit' name='ok' value='OK'>
</form>

</body>
</html>