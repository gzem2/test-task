<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <h3>Данные банка &mdash; ${bank.bankName} </h3>
    <div class="toolbar">
        <a class="button button-toolbar" href="${pageContext.request.contextPath}/credit/new?bank=${bank.id}"><div style="margin-left:5px;">Добавить кредит</div></a>
        <a class="button button-toolbar" href="${pageContext.request.contextPath}/bank/customer/new?bank=${bank.id}"><div style="margin-left:5px;">Добавить клиента</div></a>
    </div>

    <c:if test="${!empty bank.credits}">
        <table class="tg">
            <tr>
                <th colspan="2">Кредиты банка &laquo;${bank.bankName}&raquo;:</th>
            </tr>
            <tr>
                <th width="80">Id</th>
                <th width="120">Наименование кредита</th>
                <th width="60">Редактировать</th>
                <th width="60">Удалить</th>
            </tr>
            <c:forEach items="$bank.credits}" var="credit">
                <tr>
                    <td><a href="<c:url value='/bank/${bank.id}' />">${credit.id}</a></td>
                    <td><a href="<c:url value='/bank/${bank.id}' />">${credit.creditLimit}</a></td>
                    <td><a href="<c:url value='/bank/${bank.id}' />">${credit.interestRate}</a></td>
                    <td><a href="<c:url value='/bank/edit/${bank.id}' />">Редактировать</a></td>
                    <td><a href="<c:url value='/bank/delete/${bank.id}' />">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${!empty bank.customers}">
        <table class="tg">
            <tr>
                <th colspan="6">Клиенты банка &laquo;${bank.bankName}&raquo;:</th>
            </tr>
            <tr>
                <th width="80">Id</th>
                <th width="120">ФИО Клиента</th>
                <th width="120">Номер телефона</th>
                <th width="120">Электронная почта</th>
                <th width="120">Номер паспорта</th>
                <th width="60">Убрать</th>
            </tr>
            <c:forEach items="${bank.customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.email}</td>
                    <td>${customer.passport}</td>
                    <td><a href="<c:url value='/bank/remove/customer?bank=${bank.id}&customer=${customer.id}' />">Убрать</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<jsp:include page="../shared/footer.jsp" />