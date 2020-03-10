<%-- 
    Document   : item
    Created on : Feb 20, 2019, 12:46:57 PM
    Author     : mcneelyad
--%>

<%@page import = "JavaBeans.Item" %>
<%@page import = "java.util.*" %>
<%@page import = "Database.ItemDB" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page = "Partials/header.jsp"/>

<body>
    <jsp:include page = "Partials/signinStatus.jsp"/>

    <div id = "siteBranding">
        <h1>Movie Thoughts</h1>
    </div>

    <jsp:include page="Partials/user-navigation.jsp"/>

    <jsp:include page="Partials/site-navigation.jsp"/>


    <div id = "mainContent">
        <h2 id = "MovieTitle"><c:out value="${item.itemName}"></c:out></h2>
        <div id = "MoviePicture">

            <img src = "${item.imageUrl}" alt = "Movie image" height="300">
        </div>

        <div id = "MovieContent">
            <h4>Category: <c:out value="${item.catalogCategory}"></c:out></h4>
            <h4>Rating: <c:out value="${item.itemRating}"></c:out></h4>
        </div>

        <br><br>

        <form action="profile" method="post">
            <input type="hidden" name="itemCode" value="${thisItem.itemCode}"/>
            <input type="hidden" name="itemList" value="${thisItem.itemCode}"/>
            <c:if test="${inProfile != 'true'}"> <button type="submit" class="submitButton" name="action" value="save">Save it</button> </c:if>
                <button type="submit" class="submitButton" name="action" value="rate">Rate it</button>
        </form>

                <p><c:out value="${item.itemDescription}"></c:out></p>

        <br><br>
        <button type="button"><a href = "controller">Return to Categories</a></button>
    </div>

    <jsp:include page="Partials/footer.jsp"/>
