
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
<title>Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->

</head>
<body>
	<h1>Let's get you registered and settled in.</h1>
	<div class="bowser">
		<form:form class="text-align:center" action="/register" method="POST"
			modelAttribute="newUser">
			<div>
				<form:label path="userName">User Name:</form:label>
				<form:errors path="userName" />
				<form:input path="userName" />
			</div>
			<div>
				<form:label path="firstName">First Name:</form:label>
				<form:errors path="firstName" />
				<form:input path="firstName" />
			</div>
			<div>
				<form:label path="lastName">Last Name:</form:label>
				<form:errors path="lastName" />
				<form:input path="lastName" />
			</div>
			<div>
				<form:label path="email">Email:</form:label>
				<form:errors path="email" />
				<form:input type="email" path="email" />
			</div>
			<div>
				<form:label path="dob">Date Of Birth:</form:label>
				<form:errors path="dob" />
				<form:input type="date" path="dob" />
			</div>
			<div>
				<form:label path="quote">Favorite Quote:</form:label>
				<form:errors path="quote" />
				<form:textarea path="quote" />
			</div>
			<div>
				<form:label path="password">Password:</form:label>
				<form:errors path="password" />
				<form:password path="password" />
			</div>

			<!-- 			//If using form:input, you must include "type="password" -->
			<div>
				<form:label path="confirm">Confirm Password:</form:label>
				<form:errors path="confirm" />
				<form:password path="confirm" />
			</div>
			<div>
				<!-- 			NOTE: the form:password. or could be form:input type="password" -->
				<input type="submit" class="button4" value="Register" />
		</form:form>
	</div>

	<a href="/">Login</a>

	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
</body>
</html>