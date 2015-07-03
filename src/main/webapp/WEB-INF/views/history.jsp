<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" language="javascript"
        src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo_table.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css">

<script>
    $(document).ready(function () {
        $('#mytable').dataTable({
            "sPaginationType": "full_numbers"
        });
    });



</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Being Java Guys | User Details</title>
</head>
<body>
<br><br><br><br><br><br>

<div style="color: teal;font-size: 30px">Управление балансами пользователей</div>
<br><br>
<c:if test="${!empty user}">
    <table id="mytable" border="1" width="600px">
        <thead style="background-color: teal;color: white;text-align: center;">
        <tr>
            <th>Администратор</th>
            <th>Пользователь</th>
            <th>Дата пополнения</th>
            <th>Сумма пополнения</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${history}" var="history">
            <tr>
                <td><c:out value=" ${history.admin_name}"/></td>
                <td><c:out value="${history.user_name}"/></td>
                <td><c:out value="${history.update_date}"/></td>
                <td><c:out value="${history.summ}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br>

</body>
</html>