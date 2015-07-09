<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" language="javascript"
        src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo_table_jui.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="/css/my/main.css">

<script>
    $(document).ready(function () {
        $('#users').dataTable({
            "sPaginationType": "full_numbers"
        });
    });

    function updateBalance(user, id) {
        $('#balanceModal').modal({show: true})
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
                'userid': document.getElementById('id').value,
                'userName': document.getElementById('user').value
            },
            success: function (data) {
                showHidePreloader(false);
                $('#users').find('tr#' + document.getElementById('id').value).find('td:eq(1)').html(data.balance);
                alert('Баланс успешно пополнен на сумму ' + data.balance);
                $('#balanceModal').modal('hide')
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
    <title>User Details</title>
</head>
<body>
<br><br><br><br><br><br>

<div style="color: teal;font-size: 30px; text-align: center">Управление балансами пользователей</div>
<br><br>
<c:if test="${!empty user}">
    <div id="content">
        <table id="users" border="1" width="600px">
            <thead>
            <tr>
                <th>Email</th>
                <th>Баланс</th>
                <th>Дата регистрации</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${user}" var="user">
                <tr id="${user.user_id}">
                    <td>
                        <a href="javascript:;"
                           onclick="updateBalance('${user.username}','${user.user_id}')"><c:out
                                value="${user.username}"/></a></td>
                    <td id="${user.user_id}"><c:out value="${user.balance} $" /></td>
                    <td><c:out value="${user.reg_date}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<br>

<div class="modal fade hide" id="balanceModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Пополнение баланса</h3>
    </div>
    <div class="modal-body">
        <br/>
        <b>
            <div class="block">
                <label>Пользователь</label>
                <input type="text" class="input-large" name="user" id="user" value="" readonly>
                <input type="hidden" class="input-large" name="id" id="id" value="">
            </div>
            <div class="block">
                <label>Сумма</label>
                <input type="text" class="input-large" name="amt" id="amt" value="" required pattern="\d+(\.\d{2})?">
            </div>
        </b>

        <div style="display: none;" id="preloader"><img src="http://preloaders.net/images/ajax-loader.gif" alt=
                "AJAX loader" title="AJAX loader"/></div>
        <br/>

    </div>
    <div class="modal-footer">
        <a href="javascript:;" class="btn" data-dismiss="modal">Отмена</a>
        <a href="javascript:;" id="addBalanceButton" class="btn btn-primary" onclick="addAmount()"> Пополнить </a>
    </div>
</div>
</body>
</html>