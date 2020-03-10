<%-- 
    Document   : newItem
    Created on : Apr 24, 2019, 10:04:28 AM
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
        <form>
            <h2>Create a new movie</h2>

            <p>Enter the name of the movie: </p>
            <input type="text" placeholder="Name" name="name" required><br><br>

            <p>What genre is the movie? </p>
            <select name="genre">
                <option value="action">Action</option>
                <option value="sci-fi">Science Fiction</option>
                <option value="drama">Drama</option>
                <option value="comedy">Comedy</option>
                <option value="horror_thriller">Horror/Thriller</option>
                <option value="documentary">Documentary</option>

            </select><br><br>

            <p>Enter the description: </p>
            <input type="text" placeholder="Description" name="description" required><br><br>

            <p>What rating would you give it? </p>
            <input type="text" placeholder="Rating" name="rating" required><br><br><br><br>

            <button type="submit">Create</button><br><br><br>
        </form>

    </div>

    <jsp:include page="Partials/footer.jsp"/>

