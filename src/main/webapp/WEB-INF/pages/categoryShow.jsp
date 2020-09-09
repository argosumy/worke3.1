<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2020
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ShowCategory</title>
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
    <h3>Категории</h3>
    <table border="1" width="100%">
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Родительская категория</th>
                <th colspan="2"></th>
            </tr>
        <c:forEach var ="category" items="${categories}" >
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>${category.description}</td>
                <td>${category.parentId}</td>
                <td><a href="<c:url value="/admin/category/upDate/${category.id}"/>">Редактировать</a></td>>
                <td><a href="<c:url value="/admin/category/del/${category.id}"/>">Удалить</a></td>
            </tr>
        </c:forEach>
        <c:if test="${upDate}">
            <tr>ВВЕДИТЕ НОВЫЕ ПАРАМЕТРЫ ДЛЯ КАТЕГОРИИ</tr>
            <c:set var="action" value="/admin/category/updateCategory/${categories[0].id}"/>
        </c:if>
        <c:if test="${!upDate}">
            <tr>ВВОД НОВОЙ КАТЕГОРИИ</tr>
            <c:set var="action" value="/admin/category/addCategories"/>
        </c:if>
        <form action="<c:url value= '${action}'/>" method="post">
            <td>Автозаполнение</td>
            <td><input name="name" type="text"  content="НАЗВАНИЕ КАТЕГОРИИ"/></td>
            <td><input name="description" type="text"/></td>
            <td><input name="parentId" type= "number" min="1"/></td>
            <td><input type="submit" value="ВВЕСТИ" /> </td>
            <td><input type="reset" value="ОТМЕНИТЬ ВВОД" /></td>
        </form>
    </table>
<div>
    <h3><a href="<c:url value="/"/>">Главное меню</a></h3>
</div>
</body>
</html>
