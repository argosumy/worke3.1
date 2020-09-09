<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.08.2020
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Товары</title>
    <style><%@include file="css/style.css"%></style>
    <meta charset="UTF-8">
</head>
<body>
    <div>
        <h2>${product.name}</h2>
    </div>
    <div class="ramka">
        <p>${product.description}</p>
        <p style="color: #2db34a">Цена: ${product.price}</p>
    </div>
</body>
<div>
    <h3><a href="<c:url value="/showBook/1"/>">Главное меню</a></h3>
    <h3><a href="<c:url value="/showBook/${idCategory}"/>">Возврат</a></h3>
</div>
</html>
