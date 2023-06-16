

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
                UserName:<input id="username" type="text" name="username" /> 
                <br/>
                PassWord:<input type="password" name="password" />
                <br/>
                <input type="checkbox" name ="remember" value="remember"/> Remember me <br/>
                <input type="submit" value="Login" />
            </form>
    </body>
</html>
