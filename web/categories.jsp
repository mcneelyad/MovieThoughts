<%-- 
    Document   : categories
    Created on : Feb 20, 2019, 12:44:36 PM
    Author     : mcneelyad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import = "JavaBeans.Item" %>
<%@page import = "java.util.*" %>
<%@page import = "Database.ItemDB" %>

<jsp:include page = "Partials/header.jsp"/>

<body>

    <jsp:include page = "Partials/signinStatus.jsp"/>

    <div id = "siteBranding">
        <h1>Movie Thoughts</h1>
    </div>

    <jsp:include page="Partials/user-navigation.jsp"/>

    <jsp:include page="Partials/site-navigation.jsp"/>

    <div id = "mainContent">
        <h2>Categories</h2>
        <br>

        <div id = "movieList">
            <c:forEach items="${categoriesList}" var="category">
                <h3><c:out value="${category}"></c:out></h3>
                <ul>
                    <c:forEach items="${itemList}" var="item">
                        <c:if test="${item.catalogCategory eq category}">
                            <li><a href="controller?itemCode=${item.itemCode}"><c:out value = "${item.itemName}"></c:out></a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="Partials/footer.jsp"/>

