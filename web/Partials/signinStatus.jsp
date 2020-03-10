<%-- 
    Document   : signinStatus
    Created on : Apr 16, 2019, 9:27:27 AM
    Author     : mcneelyad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id = "loginName">
    <c:choose>
        <c:when test="${currentProfile != null}">
            <p>Welcome, <c:out value="${user.user.firstName}"/></p>
        </c:when>
        <c:otherwise>
            <p>Not signed in.</p>
        </c:otherwise>
    </c:choose>
</div>
