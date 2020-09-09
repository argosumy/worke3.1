<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.08.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html >
<head>
    <title>Book</title>
    <style><%@include file="css/style.css"%></style>
    <style><%@include file="css/login.css"%></style>
    <meta charset="UTF-8">
</head>
<body>
<header>
    <div class="header-top">
        <%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
        <%String authentication = SecurityContextHolder.getContext().getAuthentication().getName();%>
        <div class="logout">
            <%if(authentication.equals("anonymousUser")){%>
                <a href="<c:url value='/login' />">LOGIN</a>
            <%} else {%>
                <a href="<c:url value='/logout' />">LOGOUT login - <%=authentication%></a>
            <%}%>
        </div >
        <div>
            <nav class="one">
                <ul>
                    <li><a  href=<c:url value="/admin/category/categoryShow"/>>Работа с категориями</a></li>
                    <li><a  href=<c:url value="/admin/product/productShow"/>>Работа с товарами</a></li>
                    <li><a  href=<c:url value="/admin/user/userShow"/>>Работа с администраторами</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<h1>BOOK Dietary supplement</h1>

<c:if test="${categories.size() > 0}">
    <div class="ramka">
        <p>Категории</p>>
        <ul>
            <c:forEach var ="category" items="${categories}" >
                <li><a href="<c:url value='${category.id}'/>">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>>
</c:if>
    <div class="ramka">
        <c:if test="${products.size() < 1}">
            <p>НЕТ ПОЗИЦИЙ ДЛЯ ПРОСМОТРА</p>
        </c:if>
        <c:if test="${products.size() > 0}">
            <c:if test="${category.id > 1}">
                <a href="<c:url value="/showBook/${category.parentId}"/>"><h3 style="color: darkgoldenrod">${category.name}</h3></a>
            </c:if>
            <ul>
                <c:forEach var="product" items="${products}">
                    <li><a href="<c:url value='/showBook/product/${product.id}'/>">"${product.name}"</a></li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
<c:if test="${category.id > 1}">
    <button class="btn" type="button" onclick="location.href='<c:url value="/showBook/${category.parentId}"/>'">В предыдущее меню</button>
</c:if>
</body>
</html>
