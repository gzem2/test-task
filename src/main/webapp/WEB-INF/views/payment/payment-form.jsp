<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../shared/header.jsp" />
<div class="column column-content">
    <form:form method="post" modelAttribute="payment" action="${pageContext.request.contextPath}/payment/new?creditOffer=${creditOffer.id}">
        <table>
            <tr>
                <th colspan="2">Данные платежа</th>
            </tr>
            <tr>
                <form:hidden path="id" />
                <td>
                    <form:label path="dateOfPayment">Дата платежа:</form:label>
                </td>
                <td>
                    <form:input id="datePicker" type="date" path="dateOfPayment"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="payment">Сумма платежа:</form:label>
                </td>
                <td>
                    <form:input id="paymentSum" onkeyup="calcDebts()"  oninput="calcDebts()" value="${monthlyPayment}" path="payment" type="number" min="0"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="mainDebt">Сумма гашения тела кредита:</form:label>
                </td>
                <td>
                    <form:input id="mainDebt" path="mainDebt" type="number" min="0"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="interestDebt">Сумма гашения процентов:</form:label>
                </td>
                <td>
                    <form:input id="interestDebt" path="interestDebt" type="number" min="0"></form:input>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Сохранить" />
                    <a href="${pageContext.request.contextPath}/creditOffer/${creditOffer.id}" class="button" />Отмена</a>
                </td>
            </tr>
        </table>
    </form:form>
    <div style="display:none">
        <div id="coMainDebt">${creditOffer.mainDebt}</div>
        <div id="coInterestDebt">${creditOffer.interestDebt}</div>
        <div id="percent">${creditOffer.credit.interestRate}</div>
    </div>
</div>
<script>
function calcDebts() {
    var mainDebt = document.getElementById("coMainDebt").innerText;
    var interestDebt = document.getElementById("coInterestDebt").innerText;

    var interest = document.getElementById("percent").innerText / 100 / 12;


    var percentDebt = Math.floor(mainDebt * interest);
    document.getElementById("interestDebt").value = percentDebt;
    document.getElementById("mainDebt").value = document.getElementById("paymentSum").value - percentDebt;

}

(function() {
    document.getElementById('datePicker').valueAsDate = new Date();
    calcDebts();
})();
</script>
<jsp:include page="../shared/footer.jsp" />