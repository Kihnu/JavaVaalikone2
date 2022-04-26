<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Comparison"%>
<%@ page import="data.Candidates"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="CSS/Results.css">
<title>Results</title>
</head>
<body>

	<form method="get" action="/index.html">
		<button type="submit" class=exitbutton>Front Page</button>
	</form>

	<form method="get" action="/AnsweringServlet">
		<button type="submit" class=exitbutton>Back to Questions</button>
	</form>
	<br>
	<br>
	<h1>The results are in:</h1>

	<c:forEach var="candidates" items="${requestScope.comparison}"
		varStatus="status">
		<div class="cand">
			<input name="id" value="${comparison[status.index].c_id}"
				hidden=hidden readonly />
			<!-- Kandidaatin koko nimi -->
			<div class="name">${comparison[status.index].firstname} ${comparison[status.index].lastname}</div>
			<!-- Kandidaatin puolue -->
			<div class="party">${candidates.party}</div>
			<!-- Kandidaatin numero -->
			<div class="candidateNum">Candidate
				#${comparison[status.index].vote}</div>
			<div>
				<br>
				<%!String color;%>
				<c:choose>
					<c:when test="${comparison[status.index].comparisonPercent <= 20}">
						<%
							color = "#FF0000"; // punainen
						%>
					</c:when>
					<c:when test="${comparison[status.index].comparisonPercent <= 40}">
						<%
							color = "#E66900"; // oranssi
						%>
					</c:when>
					<c:when test="${comparison[status.index].comparisonPercent <= 60}">
						<%
							color = "#D9CA00"; // keltainen
						%>
					</c:when>
					<c:when test="${comparison[status.index].comparisonPercent <= 80}">
						<%
							color = "#86BF5E"; // hieman vihreä
						%>
					</c:when>
					<c:otherwise>
						<%
							color = "#00FF00"; // vihreä
						%>
					</c:otherwise>
				</c:choose>
				<div style="color:<%=color%>;" class="average">
					<c:out value="${comparison[status.index].comparisonPercent}" />
					%
				</div>
<a href="/SingleCandidate?id=${candidates.id}" class="infobutton">More information</a>
			</div>
			<hr class="solid">
		</div>
	</c:forEach>
</body>
</html>