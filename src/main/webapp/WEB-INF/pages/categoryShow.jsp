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
</head>
<body>
    <h3>Категории</h3>
    <!--<a href="<c:url value=""/>"Добавить категорию</a>-->
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
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td></td>
                <td></td>
                <td><a href="<c:url value="${category.id}"/>">Редактировать</a></td>>
                <td><a href="<c:url value="/delCategories/${category.id}"/>">Удалить</a></td>
            </tr>
        </c:forEach>
        <tr>ВВОД НОВОЙ КАТЕГОРИИ</tr>
        <form action="addCategories" method="post">
            <td>Категория</td>>
            <td><input name="name" type="text" content="НАЗВАНИЕ КАТЕГОРИИ"/></td>
            <td><input name="description" type="text"/></td>
            <td><input name="id" type="number" min=0/></td>
            <td><input type="submit" value="ВВЕСТИ" /> </td>
            <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
        </form>
    </table>

</body>
</html>
