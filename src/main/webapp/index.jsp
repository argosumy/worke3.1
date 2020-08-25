<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Товары</title>
    <style><%@include file="WEB-INF/pages/css/style.css"%></style>
    <style><%@include file="WEB-INF/pages/css/login.css"%></style>>
</head>
<body>
<header>
    <div class="logout">
        <a href="<c:url value='/logout' />">LOGOUT</a>
    </div>
</header>

    <h1>Справочник биологически активных добавок</h1>
    <ul class="menu-main">
        <li><a href=/admin/categoryShow>Работа с категориями</a></li>
        <li><a href=/admin/productShow>Работа с товрами</a></li>
        <li><a href=/showBook/1>Показать товары</a></li>
        <li><a href=/admin/userShow>Работа с администраторами</a></li>
    </ul>
</body>
</html>
