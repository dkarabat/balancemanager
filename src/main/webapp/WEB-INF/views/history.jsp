<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/demo_table.css">
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap-responsive.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/resources/css/my/style_old.css">

<script>
    $(document).ready(function () {
        $('#history').dataTable({
            "sPaginationType": "full_numbers"
        });
    });

    // Инициализация выбора даты
    $(function () {
        $.datepicker.setDefaults($.datepicker.regional['ru']);
        $("#dateFrom").datepicker({dateFormat: 'yy-mm-dd', constrainInput: false});
        $("#dateTo").datepicker({dateFormat: 'yy-mm-dd', constrainInput: false});
    });
</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Being Java Guys | User Details</title>
</head>
<body>
<br><br><br><br><br><br>

<div style="color: teal;font-size: 30px">Журнал пополнений баланса</div>
<br><br>

<form method="POST" action="gethistorybydate" id="filter" class="form-horizontal"
      novalidate="novalidate">
    <div class="control-group">
        <label class="control-label">Дата c:</label>

        <div class="controls">
            <input type="text" class="input-large" contenteditable="false" name="dateFrom"
                   id="dateFrom" value="${dateFrom}">
        </div>
    </div>

    <div class="control-group" id="dateFromBlock">
        <label class="control-label">Дата по:</label>

        <div class="controls">
            <input type="text" class="input-large" contenteditable="false" name="dateTo"
                   id="dateTo" value="${dateTo}">
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary"><i class="icon-filter"></i> Найти
        </button>
        <button type="reset" class="btn">Очистить</button>
    </div>
</form>

<c:if test="${!empty history}">
    <div id="content">
        <table id="history" border="1" width="600px">
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
                    <td><c:out value="${history.admin_name}"/></td>
                    <td><c:out value="${history.user_name}"/></td>
                    <td><c:out value="${history.update_date}"/></td>
                    <td><c:out value="${history.summ}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<br>

</body>
</html>