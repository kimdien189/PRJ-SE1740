

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Account account = (Account) session.getAttribute("account");
            if(account != null){
//                response.sendRedirect("Login.jsp");
                request.setAttribute("userdetail", account.getDisplayname());
            }else{
        %>
        <%
        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie ck : cookie) {
               if(ck.getName().equals("detail")){     
                request.setAttribute("userdetail", ck.getValue());
        %>


        <%}}}}
        %>
        <%if(request.getAttribute("userdetail") == null){
            response.sendRedirect("login.jsp");
        }%>

        <h4>Hello: <%=request.getAttribute("userdetail")%></h4>
    </body>
</html>
