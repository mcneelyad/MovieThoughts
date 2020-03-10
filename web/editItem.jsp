<%-- 
    Document   : editItem
    Created on : May 1, 2019, 9:02:32 PM
    Author     : mcneelyad
--%>

<%@page import = "JavaBeans.Item" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page = "item.jsp"/>

<h4> What rating would you give this movie?</h4>

<form action="" method="post">
    <select name="rating">
        <option value="1">One Star</option>
        <option value="2">Two Stars</option>
        <option value="3">Three Stars</option>
        <option value="4">Four Stars</option>
        <option value="5">Five Stars</option>
    </select>
    <br>

    <h4>Have you watched this movie yet?</h4>
    <select name = "watchedIt">
        <option value = "true">Yes</option>
        <option value = "false">No</option>
    </select>

    <input type="hidden" name="action" value="save"/>
    <input type="hidden" name="rating" value="${Item.rating}">
    <input type="hidden" name="watchedIt" value="${Item.watchedIt}">
    <input type="submit" value = "save">
    
</form>

