<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/resources/css/my/style.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title"/></title>
</head>
<body>
<div class="wrapper">
    <header class="header" id="title" style="color: teal;font-size: 30px"><spring:message code="label.balance"/></header>
    <main class="content">
        <h1>Ваш текущий баланс : ${balance} $</h1>
        <br>
        <a href="<c:url value="j_spring_security_logout" />">Выйти из системы</a>
    </main>
</div>
<footer class="footer">
    <strong>BALANCE MANAGER</strong>
</footer>
</body>
</html>