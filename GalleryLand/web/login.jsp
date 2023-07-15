<%-- 
    Document   : login
    Created on : Jul 14, 2023, 11:17:18 AM
    Author     : kimdi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Login Page in HTML with CSS Code Example</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">

    </head>
    <body>
        <!-- partial:index.partial.html -->
        <% String loginStatus = (String) request.getAttribute("loginStatus"); %>
        <div class="box-form">
            <div class="left">
                <div class="overlay">
                    <h1>Gallery Land</h1>
                    <p>Join a thriving community of artists and creators</p>
                </div>
            </div>
            <div class="right">
                <form action="login" method ="post">
                    <h5>Login</h5>
                    <div class="inputs">
                        <% if (loginStatus != null) { %>
                        <% if (loginStatus.equals("fail")) { %>
                        <p class="text-danger">Wrong username or password</p>
                        <% } %>
                        <% } %>
                        <input type="text" name="username" placeholder="Username">
                        <br>
                        <input type="password" name="password" placeholder="Password">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="remember" value="remember">
                        <label class="form-check-label" for="exampleCheck1">Remember me</label>
                    </div>
                    <p>Not a member? <a href="signup.jsp">Sign up now</a></p>

                    <button type= "submit" class="btn btn-dark">Login</button>
                </form>
            </div>

        </div>
        <!-- partial -->

    </body>
</html>
