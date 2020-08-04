<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2020
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowCategory</title>
    <style><%@include file="css/style.css"%></style>
    <meta charset="UTF-8">
</head>
<body>
    <h3>Категории</h3>
    <table border="1" width="100%">
        <th>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Родительская категория</th>
                <th colspan="2"></th>
            </tr>
        </th>
        <c:forEach var ="category" items="${categories}" >
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>${category.description}</td>
                <td>${category.parentId}</td>
                <td><a href="<c:url value="/Category/upDate/${category.id}"/>">Редактировать</a></td>>
                <td><a href="<c:url value="/Category/del/${category.id}"/>">Удалить</a></td>
            </tr>
        </c:forEach>
        <c:if test="${upDate}">
            <tr>ВВЕДИТЕ НОВЫЕ ПАРАМЕТРЫ ДЛЯ КАТЕГОРИИ</tr>
            <c:set var="action" value="/updateCategory/${categories[0].id}"/>
        </c:if>
        <c:if test="${!upDate}">
            <tr>ВВОД НОВОЙ КАТЕГОРИИ</tr>
            <c:set var="action" value="/addCategories"/>
        </c:if>
        <form action="${action}" method="post">
            <td>Автозаполнение</td>>
            <td><input name="name" type="text"  content="НАЗВАНИЕ КАТЕГОРИИ"/></td>
            <td><input name="description" type="text"/></td>
            <td><input name="id" type="number" min=0/></td>
            <td><input type="submit" value="ВВЕСТИ" /> </td>
            <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
        </form>
    </table>
<div>
    <h3><a href="<c:url value="/"/>">Главное меню</a></h3>
</div>
</body>
</html>
