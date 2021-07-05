<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
    <link href="<c:url value=" /css/main.css" />" rel="stylesheet">
    <title>test-task</title>
</head>

<body>
    <div class="column column-sidebar">
        <div class="div-sidebar-button"><a class="button button-sidebar" href="${pageContext.request.contextPath}/customers">Клиенты</a></div>
        <div class="div-sidebar-button"><a class="button button-sidebar" href="${pageContext.request.contextPath}/banks">Банки</a></div>
    </div>