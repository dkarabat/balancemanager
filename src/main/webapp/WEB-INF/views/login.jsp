<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    <br><br>
    <header  class="header" id="title" style="color: teal;font-size: 30px"><spring:message code="label.title"/></header>


    <main class="content">
        <c:if test="${not empty param.error}">
            <font color="red"> <spring:message code="label.loginerror"/>
                : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
        </c:if>
        <form method="POST" action="<c:url value='j_spring_security_check' />" method='POST'>
            <table width="500px" height="150px">
                <tr>
                    <td align="right"><spring:message code="label.login"/></td>
                    <td><input type="email" name="j_username"/></td>
                </tr>
                <tr>
                    <td align="right"><spring:message code="label.password"/></td>
                    <td><input type="password" name="j_password"/></td>
                </tr>
                <tr>
                    <td align="right"><spring:message code="label.remember"/></td>
                    <td><input type="checkbox" name="_spring_security_remember_me"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login"/>
                        <input type="reset" value="Reset"/></td>
                </tr>
            </table>
            <a href="register.html"><spring:message code="label.register"/></a>

            </a><br/>
        </form>
    </main>
</div>
<!-- .wrapper -->
<footer class="footer">
    <strong>BALANCE MANAGER</strong>
</footer>
<!-- .footer -->
</body>
</html>