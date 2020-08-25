<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.08.2020
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>userShow</title>
    <style><%@include file="css/style.css"%></style>
    <style><%@include file="css/login.css"%></style>
</head>
<body>
<header>
    <div class="logout">
        <a href="<c:url value='/logout' />">LOGOUT</a>
    </div>
</header>
<h3>Администраторы</h3>
<table border="1" width="100%">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Телефон</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th colspan="2"></th>
        </tr>
    <c:forEach var ="account" items="${myUsers}" >
        <tr>
            <td>${account.getId()}</td>
            <td>${account.getName()}</td>
            <td>${account.getLastName()}</td>
            <td>${account.getPhone()}</td>
            <td>${account.getAccount().getName()}</td>
            <td>${account.getAccount().getPassword()}</td>
            <td><a href="<c:url value="/admin/User/upDate/${account.getId()}"/>">Редактировать</a></td>>
            <td><a href="<c:url value="/admin/User/del/${account.getId()}"/>">Удалить</a></td>
        </tr>
    </c:forEach>

    <c:if test="${upDate}">
        <tr>ВВЕДИТЕ НОВЫЕ ПАРАМЕТРЫ ДЛЯ АДМИНИСТРАТОРА</tr>
        <c:set var="action" value="/admin/updateUser/${myUsers[0].id}"/>
    </c:if>
    <c:if test="${!upDate}">
        <tr>ВВОД НОВОГО АДМИНИСТРАТОРА</tr>
        <c:set var="action" value="/admin/addUser"/>
    </c:if>
    <form action="${action}" method="post">
        <tr>
            <td>Автозаполнение</td>>
            <td><input name="name" type="text"/></td>
            <td><input name="lastName" type="text" maxlength="20"/></td>
            <td><input name="phone" type="text" maxlength="10"/></td>
            <td><input name="login" type="text"/></td>
            <td><input name="password" type="text"/></td>
            <td><input type="submit" value="ВВЕСТИ"/></td>
            <td><input type="reset" value="ОТМЕНИТЬ ВВОД"/></td>
        </tr>
    </form>
</table>
<div>
    <h3><a href="<c:url value="/"/>">Главное меню</a></h3>
</div>
</body>
</html>
