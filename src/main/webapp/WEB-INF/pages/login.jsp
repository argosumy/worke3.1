<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.08.2020
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<div>
    <h3>Вход с помощью логина и пароля</h3>
</div>
<div>
    <c:if test="${param.logout != null}">
    <div>Вы успешно вышли.</div>
    </c:if>
    <c:if test="${param.error != null}">
    <div>Неверный логин или пароль!</div>
    </c:if>
    <div>
        <form action="/login" method="post">
            <div>
                <label for="username">Логин</label>
                <input type="text" id="username" name="username" >
            </div>
            <div>
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" >
            </div>
            <button type="submit" class="btn"> Войти</button>
        </form>
    </div>
</body>
</html>
