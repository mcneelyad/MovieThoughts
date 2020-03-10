<%-- 
    Document   : myItems
    Created on : Feb 20, 2019, 12:49:31 PM
    Author     : mcneelyad and nanajjar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page = "Partials/header.jsp"/>
<body>

    <jsp:include page = "Partials/signinStatus.jsp"/>

    <div id = "siteBranding">
        <h1>Movie Thoughts</h1>
    </div>

    <jsp:include page="Partials/user-navigation.jsp"/>

    <jsp:include page="Partials/site-navigation.jsp"/>

    <div id = "mainContent">
        <h2>My Thoughts</h2>

        <table>
            <tr>
                <th>Item</th>
                <th>Category</th>
                <th>My Rating</th>
                <th>Watched It</th>
                <th></th>
            </tr>

            <c:forEach var="items" items="${currentProfile}">
                <tr class="profile_row">
                    <td><c:out value = "${items.item.itemName}"></c:out></td>
                <td><c:out value = "${items.item.catalogCategory}"></c:out></td>

                <td><c:out value = "${items.rating}"></c:out></td>
                <td><c:out value = "${items.watchedIt}"></c:out></td>

                <td>
                    <form action="profile" method="post">
                        <c:forEach var="item2" items="${currentProfile}">
                            <input type="hidden" name="itemList" value="${item2.item.itemCode}"/>
                        </c:forEach>
                        <input type="hidden" name="itemCode" value="${items.item.itemCode}"/>

                        <button type="submit" class="submitButton" name="action" value="update">Update</button>
                        <button type="submit" class="submitButton" name="action" value="delete">Delete</button>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>

        <p>Click <a href="newItem.jsp">here</a> to add a new movie!</p>
        <br>
    </div>

    <jsp:include page="Partials/footer.jsp"/>
