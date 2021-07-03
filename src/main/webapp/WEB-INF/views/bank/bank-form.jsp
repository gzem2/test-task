<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <form:form method="post" modelAttribute="bank" action="${pageContext.request.contextPath}/bank/new">
        <table>
            <tr>
                <th colspan="2">Данные банка</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="bankName">Наименование банка:</form:label>
                </td>
                <td>
                    <form:input path="bankName" size="30" maxlength="30"></form:input>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Сохранить" />
                    <a href="${pageContext.request.contextPath}/banks" class="button" />Отмена</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="../shared/footer.jsp" />