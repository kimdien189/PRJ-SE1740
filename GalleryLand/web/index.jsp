<%-- 
    Document   : index
    Created on : Jul 11, 2023, 8:08:50 AM
    Author     : kimdi
--%>
<%@page import="model.Account"%>
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
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Gallery Land</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="navbar-nav me-auto mb-3 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Explore
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">For you</a></li>
                                <li><a class="dropdown-item" href="#">Popular</a></li>
                                <li><a class="dropdown-item" href="#">New</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">More...</a></li>
                            </ul>
                        </li>

                    </ul>

                </div>

                <div class="input-group col-sm-2 col-md-8 searchBar">
                    <input id="searchQueryInput" type="text" name="searchQueryInput"
                           placeholder="Search" value />
                    <button id="searchQuerySubmit" type="submit"
                            name="searchQuerySubmit">
                        <svg style="width:24px;height:24px" viewBox="0 0 24 24"><path
                            fill="#666666"
                            d="M9.5,3A6.5,6.5 0 0,1 16,9.5C16,11.11 15.41,12.59 14.44,13.73L14.71,14H15.5L20.5,19L19,20.5L14,15.5V14.71L13.73,14.44C12.59,15.41 11.11,16 9.5,16A6.5,6.5 0 0,1 3,9.5A6.5,6.5 0 0,1 9.5,3M9.5,5C7,5 5,7 5,9.5C5,12 7,14 9.5,14C12,14 14,12 14,9.5C14,7 12,5 9.5,5Z" />
                        </svg>
                    </button>
                </div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div id="login-buttons" class="collapse navbar-collapse login-user" id="navbarSupportedContent">
                    <%
Account account = (Account) session.getAttribute("account");
if (account != null) {
    // User is already authenticated, so set the user detail attribute
    request.setAttribute("userdetail", account.getDisplayname());
} else {
    // User is not authenticated, check if there is a user detail cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userdetail")) {
                // User detail cookie found, set the user detail attribute
                request.setAttribute("userdetail", cookie.getValue());
                break;  // Stop searching for cookies
            }
        }
    }
}
                    %>

                    <input type="hidden" id="account" value="<%= request.getAttribute("userdetail") %>">

                    <%
                    if (request.getAttribute("userdetail") != null) {
                        // User is already authenticated, so display personalized welcome message
                    %>
                    <button type="button" class="btn btn-dark" disabled>Welcome, <%= request.getAttribute("userdetail") %></button>
                    <% } else { %>
                    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalFormLogin">Login</button>
                    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalFormSignUp">Sign Up</button>
                    <% } %>
                </div>
            </div>
        </nav>
        <div class="modal fade" id="ModalFormLogin" tabindex="-1"
             aria-labelledby="ModalFormLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body">
                        <button type="button" class="btn-close btn-close-white"
                                data-bs-dismiss="modal" aria-label="Close"></button>
                        <div class="myform bg-dark">
                            <h1 class="text-center">Gallery Land</h1>
                            <form action="login" method="POST">
                                <div class="mb-3 mt-4">
                                    <label for="exampleInputEmail1" class="form-label">Username</label>
                                    <input type="text" class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                                </div>
                                <button type="submit" class="btn btn-light mt-3">LOGIN</button>
                                <p>Not a member? <a href="#" data-bs-toggle="modal" data-bs-target="#ModalFormSignUp">Signup now</a></p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="ModalFormSignUp" tabindex="-1"
             aria-labelledby="ModalFormLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body">
                        <button type="button" class="btn-close btn-close-white"
                                data-bs-dismiss="modal" aria-label="Close"></button>
                        <div class="myform bg-dark">
                            <h1 class="text-center">Gallery Land</h1>
                            <form>
                                <div class="mb-3 mt-4">
                                    <label for="exampleInputEmail1" class="form-label">Username</label>
                                    <input type="email" class="form-control"
                                           id="exampleInputEmail1" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Password</label>
                                    <input type="password" class="form-control"
                                           id="exampleInputPassword1">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Confirm
                                        Password</label>
                                    <input type="password" class="form-control"
                                           id="exampleInputPassword1">
                                </div>
                                <button type="submit" class="btn btn-light mt-3">Sign Up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                <a href="google.com" target="_blank">
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
                     src="images/58da6de7b6e0458374d2ef8cf74a223d.jpg"
                     alt="Card image cap">
                <h5 class="card-title">Sky</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="images/58da6de7b6e0458374d2ef8cf74a223d.jpg"
                     alt="Card image cap">
                <h5 class="card-title">Sky2awgawgawgawgawgawgawgawgwag</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="images/58da6de7b6e0458374d2ef8cf74a223d.jpg"
                     alt="Card image cap">
                <h5 class="card-title">Sky3</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="images/58da6de7b6e0458374d2ef8cf74a223d.jpg"
                     alt="Card image cap">
                <h5 class="card-title">Sky3</h5>
            </div>
            <div class="card">
                <img class="card-img-top"
                     src="images/58da6de7b6e0458374d2ef8cf74a223d.jpg"
                     alt="Card image cap">
                <h5 class="card-title">Sk4egfaegagawg</h5>
            </div>
        </div>
        <ul class="gallery-view">
            <div class="collum-view">
                <div class="image-view">
                    <img src="images/Fd6PLIRUYAA1eu9.png" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/ep304p3xzsr61.webp" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/chun-lo-astro-2.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/kdvmzon6slvy.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/undefined - Imgur.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Doctor'sPersonalityTest.jpg" loading="lazy">
                </div>
            </div>
            <div class="collum-view">
                <div class="image-view">
                    <img src="images/4cf9175e99df86c9348cb1657e3f4418.jpg"
                         loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/5d8758e8ccc4c67c4b700a42fddd563d.jpg"
                         loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/077714ff33c8e9aaa71cdc83b34c241a.jpg"
                         loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/81238981_p0.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/91173778_p0.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/108303543_p0.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/214122224.png.jfif" loading="lazy">

                </div>
            </div>
            <div class="collum-view">
                <div class="image-view">
                    <img
                        src="images/318804174_809414696792195_3356938123226488468_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/335166533_174360958294450_349101779847201842_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/336358666_1593266237859413_4641102179905908787_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/340825299_1262609987997243_3135648658459637892_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/2143436578787653.png" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Caaaaarrot_2525-1618405126347325440-0.jpg"
                         loading="lazy">
                </div>
            </div>
            <div class="collum-view">
                <div class="image-view">
                    <img src="images/doodle art2.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/EBFOisxVAAEFaBG.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/FdEJk1qacAE7job.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/FIHRDHEacAEdRZB.jfif" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/FIHSPgGaMAA1Xbz.jfif" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Fj_sUMIVIAAwISa.jfif" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Fvsy4_jaMAA52_P.jfif" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/FY_MdyfaQAAdfR4.jfif" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Fd6PLIRUYAA1eu9.png" loading="lazy">
                </div>
            </div>
            <div class="collum-view">
                <div class="image-view">
                    <img src="images/ep304p3xzsr61.webp" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/chun-lo-astro-2.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/kdvmzon6slvy.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/undefined - Imgur.jpg" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Doctor'sPersonalityTest.jpg" loading="lazy">
                </div>
                <div class="image-view">
                    <img
                        src="images/305965136_1822293411468443_6780996164183798705_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/310154049_1041705399843898_4059105897198154554_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/310520033_530604865559720_418745114902179960_n.jpg"
                        loading="lazy">
                </div>
            </div>
            <div class="collum-view">
                <div class="image-view">
                    <img
                        src="images/318804174_809414696792195_3356938123226488468_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/335166533_174360958294450_349101779847201842_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/336358666_1593266237859413_4641102179905908787_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img
                        src="images/340825299_1262609987997243_3135648658459637892_n.jpg"
                        loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/2143436578787653.png" loading="lazy">

                </div>
                <div class="image-view">
                    <img src="images/Caaaaarrot_2525-1618405126347325440-0.jpg"
                         loading="lazy">
                </div>
            </div>
        </ul>
        <script src="js/scripts.js"></script>

        <footer>
            <p>&copy; 2023 My Webpage</p>
        </footer>
    </body>
</html>
