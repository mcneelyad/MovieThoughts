<%-- 
    Document   : about
    Created on : Feb 20, 2019, 10:53:50 PM
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
        <p>This website is a place for avid movie watchers and amateurs 
            and everyone in between to post reviews and ratings of movies 
            they’ve seen or want to see!</p>
    </div>

    <jsp:include page="Partials/footer.jsp"/>
