
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Prompts</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->

</head>
<body>
	<div class="bowser">
		<h1>
			<c:out value="${loggedUser.userName}" />
			, Check out all these story prompts
		</h1>

		<c:forEach var="thisPrompts" items="${allPrompts }">

			<p>
				<a style="color: orange" href="/prompt/${thisPrompts.id }"><c:out
						value="${thisPrompts.title }"></c:out></a>
			</p>


		</c:forEach>

		<br />
		<!-- LINKS -->
		<a href="/home">Home</a> <br /> <a href="/create/prompt">Add A
			Prompt</a>

		<form action="/logout" method="POST">
			<input type="submit" class="btn btn-link" value="Log Out">
		</form>
	</div>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
</body>
</html>