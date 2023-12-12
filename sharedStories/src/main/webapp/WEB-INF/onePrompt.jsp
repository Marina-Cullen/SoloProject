
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
<title>{thisPrompts.title}</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->

</head>
<body>


	<!-- This tests to see if the person who posted the prompt.  Different greeting for the Poster vs another member -->

	<c:choose>
		<c:when test="${loggedUser.id.equals(thisPrompts.postingUser.id) }">
			<h3 style="color: orange">You submitted this question for others
				to be inspired to answer.</h3>
			<br />
			<h3 style="color: orange">${thisPrompts.title}</h3>
			<br />
			<p>${thisPrompts.prompt}</p>
		</c:when>
		<c:otherwise>
			<h4 style="color: orange">${thisPrompts.postingUser.userName}
				submitted</h4>
			<h2 style="color: orange">${thisPrompts.title}</h2>
			<h4 style="color: orange">for you to be inspired to answer.</h4>
			<br />
			<h3>
				<em>${thisPrompts.prompt}</em>
			</h3>
		</c:otherwise>
	</c:choose>

	<!-- update and delete will appear to the person who posted the prompt ONLY -->
	<c:if test="${loggedUser.id.equals(thisPrompts.postingUser.id) }">
		<a class="button" href="/prompt/${thisPrompts.id}/update">Update
			this Listing</a>
	<!-- DELETE BUTTON -->		
		<form action="/prompt/${thisPrompts.id}/delete" method="POST">
			<input type="hidden" name="_method" value="delete" /> 
			<input type="submit" class="btn btn-link" value="Delete" />
		</form>
	</c:if>


	<!-- LINKS -->
	<br />
	<a href="/home">Home</a>
	<br />
	<a href="/create/prompt">Add A Prompt</a>
	<br />
	<a href="/prompts">View List of Prompts</a>
	<form action="/logout" method="POST">
		<input type="submit" class="btn btn-link" value="Log Out">
	</form>

	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
</body>
</html>