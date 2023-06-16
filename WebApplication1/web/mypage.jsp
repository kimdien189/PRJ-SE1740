<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>JSTL Core Tags Example</title>
</head>
<body>
   <h1>JSTL Core Tags Example</h1>
   
   <%-- Set a variable --%>
   <c:set var="name" value="John Doe" />
   
   <%-- Print the variable --%>
   <p>Hello, <c:out value="${name}" /></p>
   
   <%-- If-Else statement --%>
   <c:if test="${name == 'John Doe'}">
      <p>You are John Doe.</p>
   </c:if>
   <c:choose>
      <c:when test="${name == 'Jane Doe'}">
         <p>You are Jane Doe.</p>
      </c:when>
      <c:otherwise>
         <p>You are not Jane Doe.</p>
      </c:otherwise>
   </c:choose>
   
   <%-- Loop tag --%>
   <ul>
   <c:forEach var="i" begin="1" end="5">
      <li>Item ${i}</li>
   </c:forEach>
   </ul>
   
   <%-- URL tag --%>
   <c:url var="myUrl" value="/mypage.jsp">
      <c:param name="name" value="${name}" />
   </c:url>
   <p>Click <a href="${myUrl}">here</a> to go to another page.</p>
   
   
   <%-- Import tag --%>

</body>
</html>