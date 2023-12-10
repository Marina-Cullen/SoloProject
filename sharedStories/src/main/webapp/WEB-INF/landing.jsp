
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
<title>Landing</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->

</head>
<body>

<!--BEGINING OF LOGIN FORM  -->
	<div class="bowser"> <form:form  action="/loggingIn"
		method="POST" modelAttribute="newLogin">

		<h2>Login Here</h2>

		<form:label path="email">Email:</form:label>
		<form:errors path="email" />
		<form:input type="email" path="email" />

		<form:label path="password">Password:</form:label>
		<form:errors path="password" />
		<form:password path="password" />
		<!-- 			//If using form:input, you must include "type="password"  -->

		<input type="submit" class="button4" value="Login" />


	</form:form> </div>
<!--LINK TO REGISTRATION  -->
	<p> If you are not a member, please <a href="/register">register here</a>.</p>
	
<!--JS Tags  -->	
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
</body>
</html>