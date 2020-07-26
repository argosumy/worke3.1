<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2020
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>categoryUpDate</title>
</head>
<body>
<table border="1" width="100%">
    <th>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Родительская категория</th>
        </tr>
    </th>
        <tr>
            <td>${entity.id}</td>
            <td>${entity.name}</td>
            <td></td>
            <td></td>
        </tr>
    <tr>ВВОД НОВОЙ КАТЕГОРИИ</tr>
    <form action="/addCategories/${entity.id}" method="post">
        <td>${entity.id}</td>>
        <td><input name="name" type="text" title="НАЗВАНИЕ КАТЕГОРИИ"/></td>
        <td><input name="description" type="text" /></td>
        <td><input name="parentId" type="number" min=0/></td>
        <td><input type="submit" value="ВВЕСТИ" /> </td>
        <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
    </form>
</table>
</body>
</html>
