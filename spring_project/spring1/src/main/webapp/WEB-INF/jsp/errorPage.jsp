<%-- 
    Document   : errorPage
    Created on : 30 paź 2024, 12:11:02
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error Page</h1> 
        <p>Failed URL: ${url}
        Exception: ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste"> 
       ${ste} 
        </c:forEach>
        </p>
    </body>
</html>
