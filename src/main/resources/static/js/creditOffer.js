function calcMonthlyPay() {
    var monthsTerm = parseInt(document.getElementById("monthsTerm").value);
    var creditSum = parseInt(document.getElementById("creditSum").value);
    var creditSelect = document.getElementById("creditSelect");
    var creditSelectText = creditSelect.options[creditSelect.selectedIndex].innerText.split(" ");
    var percent = parseFloat(creditSelectText[creditSelectText.length - 1].slice(0, -1));

    var interest = percent / 100 / 12;
    var x = Math.pow(1 + interest, monthsTerm);

    document.getElementById("monthlyPayment").value = parseInt((creditSum * x * interest) / (x - 1));
}

(function () {
    document.getElementById('datePicker').valueAsDate = new Date();
    calcMonthlyPay();
})();