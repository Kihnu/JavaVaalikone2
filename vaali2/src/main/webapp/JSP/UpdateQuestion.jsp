<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
 <h1>TOimiiko?</h1>
<body>
<form action='../updatequestion' method='post'>
<input type='text' name='id' value='${requestScope.questionlist.id}'>
<input type='text' name='question' value='${requestScope.questionlist.question}'>
<input type='submit' name='ok' value='OK'>
</form>

</body>
</html>