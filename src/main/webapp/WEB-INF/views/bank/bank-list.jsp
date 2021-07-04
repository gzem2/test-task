<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <h3>Список банков</h3>
    <div class="toolbar">
        <a class="button button-toolbar" href="${pageContext.request.contextPath}/bank/new">Добавить банк</a>
    </div>
    <c:if test="${!empty listOfBanks}">
        <table class="tg">
            <tr>
                <th width="80">Id</th>
                <th width="120">Наименование банка</th>
                <th width="60">Редактировать</th>
                <th width="60">Удалить</th>
            </tr>
            <c:forEach items="${listOfBanks}" var="bank">
                <tr>
                    <td><a href="<c:url value='/bank/${bank.id}' />">${bank.id}</a></td>
                    <td><a href="<c:url value='/bank/${bank.id}' />">${bank.bankName}</a></td>
                    <td><a href="<c:url value='/bank/edit/${bank.id}' />">Редактировать</a></td>
                    <td><a href="<c:url value='/bank/delete/${bank.id}' />">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<jsp:include page="../shared/footer.jsp" />