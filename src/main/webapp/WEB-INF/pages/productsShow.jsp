<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.07.2020
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Товары</title>
    <style><%@include file="css/style.css"%></style>
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
                    <li><a  href=<c:url value="/showBook/1"/>>Показать католог </a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<h3>Товары</h3>
<table border="1" width="100%">
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>1 - Активный/0 - не активный</th>
            <th>Категория</th>
            <th colspan="2"></th>
        </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.isActive}</td>
            <td>${product.getCategories().getName()}</td>
            <td><a href="<c:url value="/admin/product/upDate/${product.id}"/>">Редактировать</a></td>>
            <td><a href="<c:url value="/admin/product/del/${product.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
    <c:if test="${upDate}">
        <tr>ВВЕДИТЕ НОВЫЕ ПАРАМЕТРЫ ДЛЯ ТОВАРА</tr>
        <c:set var="action" value="/admin/product/productUpDate/${products[0].id}"/>
    </c:if>
    <c:if test="${!upDate}">
        <tr>ВВОД НОВОГО ТОВАРА </tr>
        <c:set var="action" value="/admin/product/addProduct"/>
    </c:if>
    <form action="<c:url value='${action}'/>" method="post">
        <td>Автозаполнение</td>
        <td><input name="name" type="text" placeholder="НАЗВАНИЕ ТОВАРА"/></td>
        <td><input name="description" type="text" placeholder="ОПИСАНИЕ ТОВАРА"/></td>
        <td><input name="price" type="text" placeholder="СТОИМОСТЬ"/></td>
        <td><input name="isActive" type="number" min=0 max=1 placeholder="0/1"/></td>
        <td><input name="categoryId"  type="number" min=1 placeholder="номер категории"></td>
        <td><input type="submit" value="ВВЕСТИ" /> </td>
        <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
    </form>
</table>
<div>
    <h3><a href="<c:url value="/"/>">Главное меню</a></h3>
</div>
</body>
</html>
