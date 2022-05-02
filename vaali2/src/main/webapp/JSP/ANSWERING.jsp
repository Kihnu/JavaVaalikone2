<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="data.Questionsvanha"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href=CSS/questions.css>

<title>Questions</title>
</head>
<body>
	<%!int i = 1;%>

	<form method="get" action="/index.html">
		<button type="submit" class=exitbutton>Front Page</button>
	</form>

	<h2>QUESTIONS</h2>

	<form action="/Comparison" method="POST">
		<div class="vaihtoehdot">
			<c:forEach var="questions" items="${requestScope.questionlist}">
				<br>
				<br>
				<p class="number"><%=i++%>/${questionlist.size()}
				</p>

				<!-- Kysymysten määrästä tehdään variable -->
				<c:set var="max" value="${questionlist.size()}" />
				<!-- i:n nykyisestä määrästä tehdään variable -->
				<c:set var="i" value="<%=i%>" />
				<!-- jos i on isompi kuin kysymysten määrä -->
				<c:if test="${(i > max)}">
					<!-- i:stä tulee taas 1 (Piilotin sen numeron tällä muuten numero 1 on aina näkyvissä)-->
					<input hidden="hidden" value="<%=i = 1%>">
				</c:if>

				<br>
				<br>

				<div class="question">${questions.question}
					<br> <br>
				</div>

				<div class="radiogrp">
					<input type="radio" name="answer${questions.id}" value="option1"
						id="radio_1" required><label for="radio_1">Strongly
						Disagree </label> <br> <br>
				</div>
				<div class="radiogrp">
					<input type="radio" name="answer${questions.id}" value="option2"
						id="radio_2" required><label for="radio_2">Somewhat
						Disagree </label> <br> <br>
				</div>
				<div class="radiogrp">
					<input type="radio" name="answer${questions.id}" value="option3"
						id="radio_3" checked required><label for="radio_3"
						style="transform: translateY(-25%);"><br>In between</label> <br>
					<br>
				</div>
				<div class="radiogrp">
					<input type="radio" name="answer${questions.id}" value="option4"
						id="radio_4" required><label for="radio_4">Somewhat
						Agree </label> <br> <br>
				</div>
				<div class="radiogrp">
					<input type="radio" name="answer${questions.id}" value="option5"
						id="radio_5" required><label for="radio_5">Strongly<br>Agree
					</label> <br> <br>
				</div>

				<hr class="solid">

			</c:forEach>

			<br> <br>

		</div>

		<p>
			<input type="submit" value="Submit your answers" class="button">
		</p>

	</form>
	<!-- 	<form action="/Results"> -->
	<!-- 		<button type="submit" class="button">Submit your answers</button> -->
	<!-- 	</form> -->
</body>

</html>