<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="customer" action="${pageContext.request.contextPath}/customer/new">
        <table>
            <tr>
                <th colspan="2">Данные клиента</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="customerName">ФИО Клиента:</form:label>
                </td>
                <td>
                    <form:input path="customerName" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="phone">Номер телефона:</form:label>
                </td>
                <td>
                    <form:input path="phone" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="email">Электронная почта:</form:label>
                </td>
                <td>
                    <form:input path="email" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="passport">Номер паспорта:</form:label>
                </td>
                <td>
                    <form:input path="passport" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Сохранить" />
                    <a href="${pageContext.request.contextPath}/customers" class="button" />Отмена</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="../shared/footer.jsp" />