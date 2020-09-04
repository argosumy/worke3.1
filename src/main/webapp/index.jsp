<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Товары</title>
    <style><%@include file="WEB-INF/pages/css/test.css"%></style>
</head>
<body>
<header>
    <%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
    <%String authentication = SecurityContextHolder.getContext().getAuthentication().getName();%>
    <div class="logout">
        <%if(authentication.equals("anonymousUser")){%>
        <a href="<c:url value='/login' />">LOGIN</a>
        <%} else {%>
        <div>
            <a href="<c:url value='/logout' />">Logout login - <%=authentication%></a>
        </div>>
            <%}%>
    </div >
</header>

    <h1>Справочник биологически активных добавок</h1>
    <div class="fon">
        <ul class="menu-main">
            <li><a href=<c:url value="/showBook/1"/>>Показать товары</a></li>
            <security:authorize access="hasRole('ADMIN')">
            <li><a href=<c:url value="/admin/category/categoryShow"/>>Работа с категориями</a></li>
            <li><a href=<c:url value="/admin/product/productShow"/>>Работа с товрами</a></li>
            <li><a href=<c:url value="/admin/user/userShow"/>>Работа с администраторами</a></li>
            </security:authorize>
        </ul>
    </div>
</body>
</html>
