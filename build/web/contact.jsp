<%-- 
    Document   : contact
    Created on : Feb 20, 2019, 11:05:26 PM
    Author     : mcneelyad
--%>

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
        <p>Address: 1 Hacker Way, Menlo Park, CA 94025</p>
        <p>Phone: 1-800-555-4166</p>
        <p>Email: john_smith@example.com</p>
    </div>

    <jsp:include page="Partials/footer.jsp"/>
