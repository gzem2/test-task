<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="credit" action="${pageContext.request.contextPath}/credit/new">
        <table>
            <tr>
                <th colspan="2">Данные кредита</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="creditLimit">Лимит по кредиту:</form:label>
                </td>
                <td>
                    <form:input path="creditLimit" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="interestRate">Процентная ставка:</form:label>
                </td>
                <td>
                    <form:input path="interestRate" size="30" maxlength="30"></form:input>
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