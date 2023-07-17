<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<%@page import="model.Gallery"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous">
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/styleVisit.css">
    </head>
    <body>
        <div class="image-view">
            <div class="col-md-6 h-100 left">
                <img src="${image.URL}" loading="lazy">
            </div>
            <div class="col-md-6 h-100 right">
                <div class="image_name" >
                    <p>${image.name}</p>
                </div>
                <input type="hidden" name="imageId" value="${image.ID}">
                <button id="buttonid${image.ID}" onclick="liked('${image.ID}')">
                    <i class="fa fa-thumbs-up"></i>
                    <span class="icon">Like</span>
                </button>
                <p class="image_like" id="likes${image.ID}">${image.likes} likes</p>
                <h1>Comments: </h1>
                <div class="container comment_box">
                    <div class="row d-flex justify-content-center">
                        <div class="col-md-12 col-lg-10">
                            <c:forEach var="comment" items="${comments}">
                                <div class="card-body p-4">
                                    <div class="d-flex flex-start">
                                        <img class="rounded-circle shadow-1-strong me-3"
                                             src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(23).webp" alt="avatar" width="60"
                                             height="60" />
                                        <div>
                                            <c:set var="username" value="" />
                                            <c:forEach var="account" items="${accounts}">
                                                <c:if test="${account.user_id == comment.user_id}">
                                                    <c:set var="username" value="${account.displayname}" />
                                                </c:if>
                                            </c:forEach>
                                            <p class="usernameShow">${username}</p>
                                            <div class="d-flex align-items-center mb-3">
                                                <p class="mb-0">
                                                    ${comment.comment_date}
                                                </p>
                                            </div>
                                            <p class="mb-0">
                                                ${comment.comment_text}
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                <hr class="my-0" />


                                <hr class="my-0" style="height: 1px;" />
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--
        <form action="addComment" method="post">
            <input type="hidden" name="image_ID" value="${image.ID}">
            <label for="content">Add a comment:</label>
            <input type="text" name="content" id="content">
            <button type="submit">Submit</button>
        </form>
        --%>
        <script src="js/scriptsSearch.js"></script>
    </body>
</html>
