
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 <h1>TOimiiko?</h1>
<body>
<form action='../addquestion' method='post'>
<input type='text' name='question' value=''>
<input type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="question" items="${requestScope.question}">
	<li>${question} 
	
	<%-- <a href='../deletequestion?id=${question.id}'>Delete</a> <a href='../readtoupdatequestion?id=${question.id}'>Update</a> --%>
	
	
</c:forEach>
</ol>
</body>
</html>