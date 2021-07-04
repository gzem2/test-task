<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />
<script>
    function clearSearchInput() {
        var seachForm = document.getElementById("searchForm");
        var elements = searchForm.getElementsByTagName("input");
        for (var ii=0; ii < elements.length; ii++) {
            if (elements[ii].type == "text") {
                elements[ii].value = "";
            }
        }
    }
</script>
<div class="column column-content">
    <div class="search-box">
        <form:form id="searchForm" method="post" modelAttribute="searchCustomer"
            action="${pageContext.request.contextPath}/bank/search/customer?bank=${bank.id}">
            <table>
                <tr>
                    <th colspan="3">Найти клиента в базе данных:</th>
                </tr>
                <tr>
                    <form:hidden path="id" />
                    <td>
                        <form:label path="customerName">ФИО Клиента:</form:label>
                    </td>
                    <td>
                        <div class="search-inputs">
                            <form:input path="customerName" size="30" maxlength="30"></form:input>
                            <input type="submit" class="button" value="Искать" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="phone">Номер телефона:</form:label>
                    </td>
                    <td>
                        <div class="search-inputs">
                            <form:input path="phone" size="30" maxlength="30"></form:input>
                            <input type="submit" class="button" value="Искать" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="email">Электронная почта:</form:label>
                    </td>
                    <td>
                        <div class="search-inputs">
                            <form:input path="email" size="30" maxlength="30"></form:input>
                            <input type="submit" class="button" value="Искать" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="passport">Номер паспорта:</form:label>
                    </td>
                    <td>
                        <div class="search-inputs">
                            <form:input path="passport" size="30" maxlength="30"></form:input>
                            <input type="submit" class="button" value="Искать" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <button type="button" class="button" onclick="clearSearchInput()">Очистить</button>
                        <a href="${pageContext.request.contextPath}/bank/${bank.id}" class="button" />Назад</a>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <c:if test="${!empty foundCustomers}">
        <div class="search-result">
        <table class="tg">
            <tr>
                <th colspan="2">Найденные клиенты:</th>
            </tr>
            <tr>
                <th width="80">Id</th>
                <th width="120">ФИО Клиента</th>
                <th width="120">Номер телефона</th>
                <th width="120">Электронная почта</th>
                <th width="120">Номер паспорта</th>
                <th width="60">Выбрать</th>
            </tr>
            <c:forEach items="${foundCustomers}" var="foundCustomer">
                <tr>
                    <td>${foundCustomer.id}</td>
                    <td>${foundCustomer.customerName}</td>
                    <td>${foundCustomer.phone}</td>
                    <td>${foundCustomer.email}</td>
                    <td>${foundCustomer.passport}</td>
                    <td><a href="<c:url value='/bank/customer/new/?bank=${bank.id}&customer=${foundCustomer.id}' />">Выбрать</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:if>
    <form:form method="post" modelAttribute="customer"
        action="${pageContext.request.contextPath}/bank/new/customer?bank=${bank.id}">
        <table>
            <tr>
                <th colspan="2">Новый клиент банка ${bank.bankName}:</th>
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
                    <input type="submit" class="button" value="Добавить" />
                    <a href="${pageContext.request.contextPath}/bank/${bank.id}" class="button" />Отмена</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="../shared/footer.jsp" />