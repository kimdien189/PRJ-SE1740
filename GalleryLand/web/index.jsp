<%-- 
    Document   : index
    Created on : Jul 11, 2023, 8:08:50 AM
    Author     : kimdi
--%>
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
        <link rel="stylesheet" type="text/css" href="css/style.css">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Gallery Land</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="navbar-nav me-auto mb-3 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="home">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Explore
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="home">For you</a></li>
                                <li><a class="dropdown-item" href="home">Popular</a></li>
                                <li><a class="dropdown-item" href="home">New</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="home">More...</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <form action="search" method="post" class="input-group col-sm-2 col-md-8 searchBar">
                    <input id="searchQueryInput" type="text" name="searchQueryInput"
                           placeholder="Search" value />
                    <button id="searchQuerySubmit" type="submit"
                            name="searchQuerySubmit">
                        <svg style="width:24px;height:24px" viewBox="0 0 24 24"><path
                            fill="#666666"
                            d="M9.5,3A6.5,6.5 0 0,1 16,9.5C16,11.11 15.41,12.59 14.44,13.73L14.71,14H15.5L20.5,19L19,20.5L14,15.5V14.71L13.73,14.44C12.59,15.41 11.11,16 9.5,16A6.5,6.5 0 0,1 3,9.5A6.5,6.5 0 0,1 9.5,3M9.5,5C7,5 5,7 5,9.5C5,12 7,14 9.5,14C12,14 14,12 14,9.5C14,7 12,5 9.5,5Z" />
                        </svg>
                    </button>
                </form>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent,"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div id="login-buttons" class="login-user">
                    <% Account account = (Account) request.getAttribute("account"); %>
                    <% if (account != null) { %>
                    <ul class="navbar-nav me-auto mb-3 mb-lg-0">
                        <li class="nav-item dropdown" id="usermenu">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Welcome <%= account.getDisplayname() %>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" id="">
                                <li><a class="dropdown-item" href="#">Profile</a></li>
                                <li><a class="dropdown-item" href="changeInfoCheck.jsp">Change info</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="logout">Log out</a></li>
                            </ul>
                        </li>
                    </ul>
                    <% } %>
                    <% if (account == null) { %>
                    <form action="login.jsp" method="get"><button type="submit" class="btn btn-dark" >Login</button></form>
                    <form action="signup.jsp" method="get"><button type="submit" class="btn btn-dark" >Sign Up</button></form>
                    <% } %>
                </div>
            </div>
        </nav>
        <div class="fab-container">
            <div class="fab shadow">
                <div class="fab-content">
                    <i class="fa-solid fa-caret-up"></i>
                </div>
            </div>
            <div class="sub-button shadow" id="to-top-btn">
                <a id="to-top-btn">
                    <i class="fa-solid fa-arrow-up" ></i>
                </a>
            </div>
            <div class="sub-button shadow">
                <a href="home">
                    <i class="fa-solid fa-rotate-right"></i>
                </a>
            </div>
            <div class="sub-button shadow">
                <a href="google.com" target="_blank">
                    <i class="fa-solid fa-star"></i>
                </a>
            </div>
            <div class="sub-button shadow">
                <a href="google.com" target="_blank">
                    <i class="fa-solid fa-question"></i>
                </a>
            </div>
        </div>
        <div class="interests">
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1QuEH1qEL0XAdjxTfv1zv3cwaX5ciiEM8"
                     alt="Card image cap">
                <h5 class="card-title">Colorful</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1XKvg9j4zle0YQgFKFaw-S7V4HNdJHL3W"
                     alt="Card image cap">
                <h5 class="card-title">Yellow</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1ApppZGQornEvYC7bdquoOs0tELeealnU"
                     alt="Card image cap">
                <h5 class="card-title">Photo</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=18lkVSKSg_fCJhpRCIrJMQjOnbSAK6T1O"
                     alt="Card image cap">
                <h5 class="card-title">Art</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1CA5O6WGz-T7RlNZEzKrY5r4U1BWYOzfD"
                     alt="Card image cap">
                <h5 class="card-title">Landscape</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1QuEH1qEL0XAdjxTfv1zv3cwaX5ciiEM8"
                     alt="Card image cap">
                <h5 class="card-title">Portrait</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="https://drive.google.com/uc?id=1g35HDx4rSWipqlFpSu7BEcFkV1jRpWKo"
                     alt="Card image cap">
                <h5 class="card-title">Nature</h5>
            </div>
        </div>
        <ul class="gallery-view">
            <div class="collum-view" id="col1">
            </div>
            <div class="collum-view" id="col2">
            </div>
            <div class="collum-view" id="col3">
            </div>
            <div class="collum-view" id="col4">
            </div>
            <div class="collum-view" id="col5">
            </div>
            <div class="collum-view" id="col6">
            </div>
        </ul>
        <script src="js/scripts.js"></script>

        <footer>
            <p>&copy; 2023 My Webpage</p>
        </footer>
    </body>
</html>
