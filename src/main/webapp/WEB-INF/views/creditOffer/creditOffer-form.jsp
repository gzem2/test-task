<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="creditOffer" action="${pageContext.request.contextPath}/creditOffer/new?bank=${bank.id}">
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
                    <form:select class="inputCreditOffer" path="credit">
                        <c:forEach items="${bank.credits}" var="credit">
                            <form:option value="${credit.id}">Лимит: ${credit.creditLimit} - Ставка: ${credit.interestRate}%</form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="creditSum">Сумма кредита:</form:label>
                </td>
                <td>
                    <form:input type="number" class="inputCreditOffer" path="creditSum" size="30" maxlength="30"></form:input>
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

<jsp:include page="../shared/footer.jsp" />