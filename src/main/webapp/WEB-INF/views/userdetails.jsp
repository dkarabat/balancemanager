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


    function checkReverse(user, id) {
        $('#reverseModal').modal({show: true})
        $("#user").val(user);
        $("#id").val(id);
    }

    // Показ и скрытие прелоадера в диалоге
    function showHidePreloader(show) {
        if (show) {
            document.getElementById('preloader').style.display = 'block';
        }
        else
            document.getElementById('preloader').style.display = 'none';
    }

    // Запрос на complete
    function addAmount() {
        showHidePreloader(true);
        $.ajax({
            type: 'POST',
            url: 'popolnenie',
            dataType: "json",
            data: {
                'amount': document.getElementById('amt').value,
                'userid': document.getElementById('id').value
            },
            success: function (data) {
                showHidePreloader(false);
//                document.getElementById(data.id).value = data.balance;
                $('#mytable').find('tr#'+document.getElementById('id').value).find('td:eq(1)').html(data.balance);
                    alert('Баланс успешно пополнен на сумму' + data.balance);
                    $('#reverseModal').modal('hide')
            },
            error: function (data) {
                showHidePreloader(false);
                alert('Ошибка пополнения!');
            }
        });
    }


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
            <th>Email</th>
            <th>Баланс</th>
            <th>Дата регистрации</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${user}" var="user">
            <tr id = "${user.user_id}">
                <td>
                    <a href="javascript:;"
                       onclick="checkReverse('${user.username}','${user.user_id}')"><c:out value="${user.username}"/></a></td>
                <td id ="${user.user_id}"><c:out value="${user.balance}"/></td>
                <td><c:out value="${user.reg_date}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br>

<div class="modal fade hide" id="reverseModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Пополнение баланса</h3>
    </div>
    <div class="modal-body">
        <br/>
        <b>
            <div id="reverseText">
                Пополнение баланса
                <input type="text" class="input-large" name="user" id="user" value="">
                <input type="hidden" class="input-large" name="id" id="id" value="">
            </div>
        </b>

        Сумма :
        <input type="text" class="input-large" name="amt" id="amt" value="">

        <div style="display: none;" id="preloader"><img src="http://preloaders.net/images/ajax-loader.gif" alt=
                "AJAX loader" title="AJAX loader"/></div>

        <br/>


    </div>
    <div class="modal-footer">
        <a href="javascript:;" class="btn" data-dismiss="modal">Отмена</a>
        <a href="javascript:;" id="reverseButton" class="btn btn-primary" onclick="addAmount()"> Пополнить </a>
    </div>
</div>
</body>
</html>