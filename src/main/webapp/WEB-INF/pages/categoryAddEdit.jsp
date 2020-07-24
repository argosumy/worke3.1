<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.07.2020
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <title>categoryAddEdit</title>
</head>
<body>
<h1>Меню категорий</h1>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>ID категории</th>
        <th>Название категории</th>
        <th>Описание категории</th>
        <th>Родительская категория ID</th>
    </tr>
    <c:if test="${categories == null}">
        <h1>У вас нет категорий</h1>
    </c:if>
    <c:forEach var="category" items="${categories}">
    <tr>
        <td><c:out value="${category.id}" default="NO" /></td>
        <td><c:out value="${category.name}" default="NO"/></td>
        <td><c:out value="${}" default="NO"/></td>>
        <td><c:out value="${}" default="NO"/></td>>
    </tr>
    </c:forEach>
</table>
<h1>ВВЕДИТЕ НОВУЮ КАТЕГОРИЮ</h1>
    <form action="addCategories" method="post">
        <p>Название категории: <input name="name" type="text"/></p>
        <p>Описание категории: <input name="description" type="text"/></p>
        <p>Родительская категория: <input name="id" type="number" min=0/></p>
        <p><input type="submit" value="ВВЕСТИ" /> <input type="reset" value="ОТМЕНИТЬ ВВОД" /></p>
    </form>
</body>
</html>
