<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.08.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <style><%@include file="css/style.css"%></style>
    <meta charset="UTF-8">
</head>
<body>

<h2>BOOK Dietary supplement</h2>
<div>
    <ul>
        <c:forEach var ="category" items="${categories}" >
            <li><a href="/showBook/${category.id}">${category.name}</a></li>
        </c:forEach>
    </ul>
</div>>
<div>
    <ul>
        <c:forEach var="product" items="${products}">
            <li><a href="/showBook/product/${product.id}">"${product.name}"</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
