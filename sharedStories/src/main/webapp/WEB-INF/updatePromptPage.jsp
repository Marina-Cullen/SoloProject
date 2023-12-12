
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
<title>Update Prompt</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->

</head>
<body>
	<div class="bowser">

		<h4 style="color: orange">
			<em>Easily update your prompt here.</em>
		</h4>
		<br/>
		<!-- ADD PROMPT FORM -->
		<form:form action="/prompt/${updatedPrompt.id}/update" method="POST"
			modelAttribute="updatedPrompt">
			<input type="hidden" name="_method" value="PUT" />
			<div>
				<form:label path="title">Title:</form:label>
				<form:errors path="title" />
				<form:input path="title" />
			</div>
			<div>
				<form:label path="category">Category:</form:label>
				<form:errors path="category" />
				<!-- DROPDOWN WITH PRESET CATEGORIES -->
				<form:select path="category" items="${ allCategories }" />
			</div>
			<div>
				<form:label path="prompt">Prompt:</form:label>
				<form:errors path="prompt" />
				<form:textarea path="prompt" />
			</div>
			<br/>
<!-- HIDDEN INPUT TO LOG WHO SUBMITTED THIS PROMPT... HELPFUL TO RUN OTHER FEATURES -->
			<form:hidden path="postingUser"
				value="${updatedPrompt.postingUser.id}" />
			<input type="submit" value="Update This Prompt" />
		</form:form>
		<br /> <br /> <br />
		<!-- LINKS -->
		<a href="/home">Home</a> <br /> <a href="/prompts">All Prompts</a>
		<form action="/logout" method="POST">
			<input type="submit" class="btn btn-link" value="Log Out">
		</form>
	</div>

	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
</body>
</html>