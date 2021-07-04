<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../shared/header.jsp" />

<div class="column column-content">
    <h3>Список клиентов</h3>
    <div class="toolbar">
        <a class="button button-toolbar" href="${pageContext.request.contextPath}/customer/new">Добавить
            клиента</a>
    </div>
    <c:if test="${!empty listOfCustomers}">
        <table class="tg">
            <tr>
                <th width="80">Id</th>
                <th width="120">ФИО Клиента</th>
                <th width="120">Номер телефона</th>
                <th width="120">Электронная почта</th>
                <th width="120">Номер паспорта</th>
                <th width="60">Редактировать</th>
                <th width="60">Удалить</th>
            </tr>
            <c:forEach items="${listOfCustomers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.email}</td>
                    <td>${customer.passport}</td>
                    <td><a href="<c:url value='/customer/edit/${customer.id}' />">Редактировать</a></td>
                    <td><a href="<c:url value='/customer/delete/${customer.id}' />">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<jsp:include page="../shared/footer.jsp" />