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
        <p>Please enter your email and password to sign in.</p>

        <c:out value="${message}"/>
        <form action="login" method="post">

            <p><strong>Email: </strong></p>
            <input type="text" placeholder="Enter Email" name="email" required>

            <br><br>

            <p><strong>Password: </strong></p>
            <input type="password" placeholder="Enter Password" name="password" required>

            <br><br>

            <button type="submit">Sign In</button>

            <br><br>

        </form>

        <p>Don't have an account? Click <a href = "register.jsp">here</a> to sign up!</p>
    </div>

    <jsp:include page="Partials/footer.jsp"/>