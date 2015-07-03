<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Добро пожаловать!</title>

</head>
<body>
<center>
<br><br><br><br><br><br>
<div style="color: teal;font-size: 30px">Регистрация</div>
<br><br>
<c:url var="userRegistration" value="saveuser.html"/>
<form:form id="registerForm" modelAttribute="user" method="post" action="${userRegistration}">
<table width="400px" height="150px">
<tr>
<td><form:label path="username">Email</form:label></td>
<td><form:input  path="username"/></td>
</tr>
<tr>
<td><form:label path="password">Password</form:label></td>
<td><form:input  path="password"/></td>
</tr>
<tr>
<td><form:label path="password">Password</form:label></td>
<td><form:input path="password"/></td>
</tr>
<tr><td></td><td>
<input type="submit" value="register" />
</td></tr>
</table>
</form:form>

<br>
<a href="userlist.html" >Click Here to see User List</a>
</center>
</body>
</html>