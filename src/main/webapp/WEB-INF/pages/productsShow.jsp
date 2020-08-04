<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.07.2020
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Товары</title>
    <style><%@include file="css/style.css"%></style>
    <meta charset="UTF-8">
</head>
<body>
<h3>Товары</h3>
<!--<a href="<c:url value=""/>"Добавить категорию</a>-->
<table border="1" width="100%">
    <th>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>1 - Активный/0 - не активный</th>
            <th>Категория</th>
            <th colspan="2"></th>
        </tr>
    </th>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.isActive}</td>
            <td>${product.getCategories().getName()}</td>
            <td><a href="<c:url value="/Product/upDate/${product.id}"/>">Редактировать</a></td>>
            <td><a href="<c:url value="/Product/del/${product.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
    </table>
<table border="1" width="100%">
    <c:if test="${upDate}">
        <tr>ВВЕДИТЕ НОВЫЕ ПАРАМЕТРЫ ДЛЯ ТОВАРА</tr>
        <c:set var="action" value="/productUpDate/${products[0].id}"/>
    </c:if>
    <c:if test="${!upDate}">
        <tr>ВВОД НОВОГО ТОВАРА </tr>
        <c:set var="action" value="/addProduct"/>
    </c:if>
    <form action="${action}" method="post">
        <td><input name="name" type="text" placeholder="НАЗВАНИЕ ТОВАРА"/></td>
        <td><input name="description" type="text" placeholder="ОПИСАНИЕ ТОВАРА"/></td>
        <td><input name="price" type="text" placeholder="СТОИМОСТЬ"/></td>
        <td><input name="isActive" type="number" min=0 max=1 placeholder="0/1"/></td>
        <td><input name="categoryId" type="number" min=1 placeholder="номер категории"></td>
        <td><input type="submit" value="ВВЕСТИ" /> </td>
        <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
    </form>
</table>
<div>
    <h3><a href="<c:url value="/"/>">Главное меню</a></h3>
</div>
</body>
</html>
