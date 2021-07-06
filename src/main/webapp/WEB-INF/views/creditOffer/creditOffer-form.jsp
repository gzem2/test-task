<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="creditOffer"
        action="${pageContext.request.contextPath}/creditOffer/new?bank=${bank.id}">
        <table>
            <tr>
                <th colspan="2">Данные кредитного предложения</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="customer">Клиент:</form:label>
                </td>
                <td>
                    <form:select class="inputCreditOffer" path="customer">
                        <c:forEach items="${bank.customers}" var="customer">
                            <form:option value="${customer.id}">${customer.customerName}</form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="credit">Кредит:</form:label>
                </td>
                <td>
                    <form:select id="creditSelect" class="inputCreditOffer" path="credit">
                        <c:forEach items="${bank.credits}" var="credit">
                            <form:option value="${credit.id}">Лимит: ${credit.creditLimit} - Ставка:
                                ${credit.interestRate}%</form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="startDate">Дата оформления:</form:label>
                </td>
                <td>
                    <form:input id="datePicker" type="date" path="startDate"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="monthsTerm">Срок кредита (в месяцах):</form:label>
                </td>
                <td>
                    <form:input id="monthsTerm" onkeyup="calcMonthlyPay()"  oninput="calcMonthlyPay()" type="number" min="0" path="monthsTerm" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="creditSum">Сумма кредита:</form:label>
                </td>
                <td>
                    <form:input id="creditSum" onkeyup="calcMonthlyPay()"  oninput="calcMonthlyPay()" type="number" min="0" class="inputCreditOffer" path="creditSum" step="1000"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <label path="monthlyPayment">Ежемесячный платеж:</label>
                </td>
                <td>
                    <input id="monthlyPayment" path="monthlyPayment" type="number" disabled="true"></input>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Сохранить" />
                    <a href="${pageContext.request.contextPath}/bank/${bank.id}" class="button" />Отмена</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script src="<c:url value=" /js/creditOffer.js" />"></script>
<jsp:include page="../shared/footer.jsp" />