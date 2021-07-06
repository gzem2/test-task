<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="credit" action="${pageContext.request.contextPath}/credit/new?bank=${bank.id}">
        <table>
            <tr>
                <th colspan="2">Данные тарифного плана</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="creditLimit">Лимит по кредиту:</form:label>
                </td>
                <td>
                    <form:input type="number" min="0" path="creditLimit" step="1000" ></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="interestRate">Процентная ставка (годовая):</form:label>
                </td>
                <td>
                    <span class="percentInput"><form:input path="interestRate" type="number" max="100" accuracy="2" step="0.1" min="0"></form:input>%</span>
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