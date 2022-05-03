<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.SingleCandidateAnswers"%>
<%@ page import="data.Candidates"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href=CSS/Allcandidates.css>
<title>Candidates name</title>
</head>
<body>


	<input type="button" value="Back" class="exitbutton"
		onclick="history.back()">
	<br>

	<!-- Kuva tietystä ehdokkaasta -->

	<h1>More Information on Candidate</h1>
	<%!int i = 1;%>

	<table>
		<tr>
			<td><b>Candidate number:</b></td>
			<td>${requestScope.candidate.vote_nro}</td>
		</tr>

		<!-- 2 -->
		<tr>
			<td><b>Name:</b></td>
			<td>${requestScope.candidate.firstname}
				${requestScope.candidate.surname}</td>
		</tr>

		<tr>
			<td><b>Party:</b></td>
			<td>${requestScope.candidate.party}</td>
		</tr>

		<tr>
			<td><b>Age:</b></td>
			<td>${requestScope.candidate.age}</td>
		</tr>

		<tr>
			<td><b>Profession:</b></td>
			<td>${requestScope.candidate.profession}</td>
		</tr>

		<tr>
			<td><b>Extra information:</b></td>
			<td>${requestScope.candidate.why}</td>
		</tr>

		<tr>
			<td><b>What more:</b></td>
			<td>${requestScope.candidate.what}</td>
		</tr>

	</table>


	<h2>More Information on Candidates answers to questions</h2>

	<c:forEach var="candi" items="${requestScope.singleCandidate}">
		<div class="allCandidatesEdit">

			<h3>
				Question
				<%=i%>: ${candi.question} 


	
			</h3>
			
			<!-- Kysymysten määrästä tehdään variable -->
				<c:set var="max" value="${singleCandidate.size()}" />
				<!-- i:n nykyisestä määrästä tehdään variable -->
				<c:set var="num" value="<%=i%>" />
				<!-- jos i on isompi kuin kysymysten määrä -->
				<input hidden="hidden" value="<%=i++%>">
				<c:if test="${(num >= max)}">
					<!-- i:stä tulee taas 1 (Piilotin sen numeron tällä muuten numero 1 on aina näkyvissä)-->
					<input hidden="hidden" value="<%=i = 1%>">
				</c:if>

			<%-- <div>${candi.answer_int}</div> --%>
			<!--  -->
			<c:choose>
				<c:when test="${candi.answer_int == 1}">
						Strongly Disagree 
				</c:when>
				<c:when test="${candi.answer_int == 2}">
					Somewhat Disagree 
				</c:when>
				<c:when test="${candi.answer_int == 3}">
					In between
				</c:when>
				<c:when test="${candi.answer_int == 4}">
					Somewhat Agree
				</c:when>
				<c:otherwise>
					Strongly Agree 
				</c:otherwise>
			</c:choose>
		</div>

	</c:forEach>
	

</body>
</html>
