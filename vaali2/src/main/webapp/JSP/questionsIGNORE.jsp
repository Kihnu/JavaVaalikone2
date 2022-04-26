<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Questions"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questions</title>

<link rel="stylesheet" type="text/css" href="mycssfilesomewhere.css">
<script src="myscriptfile.js"></script>

</head>
<body>
	<h2 style="color: red">Kyssäri</h2>
	<ol>
		<c:forEach var="questions" items="${requestScope.questionlist}">
			<div style="font-size:36px; background-color:orange;" align="center">${questions.id}/${questionlist.size()}: </div>
				<div align="center">${questions.question} <br><br></div>
		</c:forEach>
	</ol>

	<!-- @SuppressWarnings("unchecked")
ArrayList<Questions> questionList = (ArrayList<Questions>) request.getAttribute("questionlist");

for (int i = 0; questionList != null && i < questionList.size(); i++) {
	Questions q = questionList.get(i);
	out.println(q.getId() + ": " + q.getQuestion());
}
}
%>  -->

	<%-- comment <%@ include file="../html/somehtml.html" %>--%>



</body>
</html>