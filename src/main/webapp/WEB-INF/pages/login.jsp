<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.08.2020
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация</title>
    <style><%@include file="css/login.css"%></style>
</head>
<body>
<div class="centrHader">
    <h3>Вход с помощью логина и пароля</h3>
</div>
<div>
    <c:if test="${param.logout != null}">
    <div class="centr">Вы успешно вышли.</div>
    </c:if>
    <c:if test="${param.error != null}">
    <div class="centr eror">Неверный логин или пароль!</div>
    </c:if>
    <div class="centr">
        <form action='/login' method="post">
            <div>
                <label for="username">Логин</label>
                <input class="size" type="text" id="username" name="username" >
            </div>
            <div>
                <label for="password">Пароль</label>
                <input class="size" type="password" id="password" name="password" >
            </div>
            <button type="submit" class="btn"> Войти</button>
        </form>
    </div>
</body>
</html>
