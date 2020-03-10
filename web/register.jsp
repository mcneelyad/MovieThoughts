<%-- 
    Document   : register
    Created on : Apr 8, 2019, 2:52:17 PM
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
        <h2>Create your account</h2>

        <form>
            <p>Enter your first name: </p>
            <input type="text" placeholder="First Name" name="reg_firstName" required>

            <br><br>

            <p>Enter your last name: </p>
            <input type="text" placeholder="Last Name" name="reg_lastName" required>

            <br><br>

            <p>Enter your email: </p>
            <input type="text" placeholder="Email" name="reg_email" required>

            <br><br>

            <p>Enter your password: </p>
            <input type="password" placeholder="Password" name="reg_password" required>

            <br><br>

            <p>Confirm your password: </p>
            <input type="password" placeholder="Confirm" name="reg_confirm_password" required>

            <br><br><br>

            <button type="submit">Register</button>

            <br><br>
        </form>
    </div>

    <jsp:include page="Partials/footer.jsp"/>
