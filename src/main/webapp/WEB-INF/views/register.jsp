<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" language="javascript"
        src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
    function checkPasswordMatch() {
        var password = $("#txtNewPassword").val();
        var confirmPassword = $("#txtConfirmPassword").val();

        if (password != confirmPassword)
            $("#divCheckPasswordMatch").html("Passwords do not match!");
        else
            $("#divCheckPasswordMatch").html("Passwords match.");
    }

    $(document).ready(function () {
        $("#txtConfirmPassword").keyup(checkPasswordMatch);
    });

    function info() {
        <%if (request.getAttribute("info") != null) out.write("alert(\""+request.getAttribute("info")+"\")"); %>
    }
</script>

<style>
    .error {
        color: #ff0000;
        font-style: italic;
        font-weight: bold;
    }
</style>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Добро пожаловать!</title>

</head>
<body onload="info()">
<center>
    <br><br><br><br><br><br>

    <div style="color: teal;font-size: 30px">Регистрация</div>
    <br><br>
    <c:url var="userRegistration" value="saveuser.html"/>
    <form:form id="registerForm" modelAttribute="user" method="post" action="${userRegistration}">
        <table width="400px" height="150px">
            <tr>
                <td><form:label path="username">Email</form:label></td>
                <td><form:input type="email" path="username" cssClass="error"/></td>
                <td><form:errors path="username" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:input type="password" id="txtNewPassword" path="password" cssClass="error"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword">Confirm password</form:label></td>
                <td><form:input type="password" id="txtConfirmPassword" path="confirmPassword" cssClass="error"
                                onChange="checkPasswordMatch();"/></td>
                <td><form:errors  path="confirmPassword" cssClass="error"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    </div>
                    <div class="registrationFormAlert" id="divCheckPasswordMatch">
                    </div>
                    <input type="submit" value="register"/>
                </td>
            </tr>
        </table>
    </form:form>

</center>
</body>
</html>