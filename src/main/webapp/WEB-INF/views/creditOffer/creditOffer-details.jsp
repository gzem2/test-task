<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <h3>Кредит на клиента: ${creditOffer.customer.customerName}</h3>
    <table class="tg">
        <tr>
            <th colspan="9">Данные кредита</th>
        </tr>
        <tr>
            <th width="80">Id</th>
            <th width="120">Клиент</th>
            <th width="120">Лимит по кредиту</th>
            <th width="120">Процентная ставка</th>
            <th width="120">Сумма кредита</th>
            <th width="120">Дата оформления</th>
            <th width="120">Срок кредита (месяцы)</th>
            <th width="60">Редактировать</th>
            <th width="60">Удалить</th>
        </tr>
        <tr>
            <td>${creditOffer.id}
            </td>
            <td>${creditOffer.customer.customerName}
            </td>
            <td>${creditOffer.credit.creditLimit}
            </td>
            <td>${creditOffer.credit.interestRate}%
            </td>
            <td>${creditOffer.creditSum}
            </td>
            <td>${creditOffer.startDate}
            </td>
            <td>${creditOffer.monthsTerm}
            </td>
            <td><a href="<c:url value='/creditOffer/edit/${creditOffer.id}?bank=${bank.id}' />">Редактировать</a>
            </td>
            <td><a href="<c:url value='/creditOffer/delete/${creditOffer.id}?bank=${bank.id}' />">Удалить</a>
            </td>
        </tr>
    </table>
    <table class="tg">
        <tr>
            <th colspan="2">Долг по кредиту</th>
        </tr>
        <tr>
            <th width="120">Сумма тела кредита</th>
            <th width="120">Сумма процентов</th>
        </tr>
        <tr>
            <td>${creditOffer.mainDebt}
            </td>
            <td>${creditOffer.interestDebt}
            </td>
        </tr>
    </table>
    <div class="toolbar">
        <a class="button button-toolbar" href="${pageContext.request.contextPath}/payment/new?creditOffer=${creditOffer.id}">
            <div style="margin-left:5px;">Добавить платеж</div>
        </a>
    </div>
    <c:if test="${!empty payments}">
        <table class="tg">
            <tr>
                <th colspan="7">Платежи</th>
            </tr>
            <tr>
                <th width="80">Id</th>
                <th width="120">Платеж номер</th>
                <th width="120">Дата платежа</th>
                <th width="120">Сумма платежа</th>
                <th width="60">Сумма гашения тела кредита</th>
                <th width="60">Сумма гашения процентов</th>
            </tr>
            <c:forEach items="${payments}" var="payment">
                <tr>
                    <td>${payment.id}</td>
                    <td>${payment.paymentNum}</td>
                    <td>${payment.dateOfPayment}</td>
                    <td>${payment.payment}</td>
                    <td>${payment.mainDebt}</td>
                    <td>${payment.interestDebt}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<jsp:include page="../shared/footer.jsp" />