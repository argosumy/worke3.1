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
    <title>Start</title>
</head>
<body>
<h1>Start program</h1>
<h1>ВВЕДИТЕ НОВУЮ КАТЕГОРИЮ</h1>
    <form action="/addCategories" method="post">
        <p>Название категории: <input name="name" type="text"/></p>
        <p>Описание категории: <input name="description" type="text"/></p>
        <p>Родительская категория: <input name="id" type="number" min=0/></p>
        <p><input type="submit" value="ENTER" /> <input type="reset" value="RESET" /></p>
    </form>
</body>
</html>
