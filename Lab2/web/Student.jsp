<%-- 
    Document   : Student
    Created on : May 24, 2023, 8:08:21 AM
    Author     : kimdi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import =" java.util.ArrayList"%>
<%@page import =" model.Student"%>
<%@page import =" java.text.SimpleDateFormat" %>
<%@page import =" java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CLASSWORK 2</title>
        <%
            ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
        %>
        <%!
            public String convertDate(Date d){
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = formatter.format(d);
                return strDate;
            }
        %> 
        <style>
            table,th,td
            {
                border:1px solid black;
            }
        </style>
    </head>
    <body>
        <form action="StudentServlet" method="post">
            <td>Number of student: </td>
            <td><input type="number" name="noStudent"/></td>
            <td><input type="submit" value="Generate"></td>
            <% if(students != null){ %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>DOB</th>
                    </tr>
                </thead>

                <tbody>
                    <% for(Student s: students){ %>
                    <tr>
                        <td><%= s.getId() %></td>
                        <td><%= s.getName() %></td>
                        <td><%
                        if(s.getGender()==true){ %>
                            <input type="checkbox" onclick="return false"  checked>
                            <%}else{%>
                            <input type="checkbox" onclick="return false">
                            <%}%></c:out></td>
                        <td><%= convertDate(s.getDOB()) %></td>
                    </tr>
                    <%}%> 
                </tbody>
            </table>
            <%}%>  
        </form>

    </body>
</html>
