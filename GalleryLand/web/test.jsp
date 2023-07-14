<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<%@page import="model.Gallery"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gallery Test</title>
    </head>
    <body>
        <h1>Gallery Images</h1>
    <h1>${mess}</h1>
    <form action="gallery" method="get">
        <button type="submit">Search</button>
    </form>
        <c:forEach items="${images}" var="o">
            <div class="image-view">
                <img src="${o.URL}" loading="lazy">
            </div>
        </c:forEach>
    </body>
</html>