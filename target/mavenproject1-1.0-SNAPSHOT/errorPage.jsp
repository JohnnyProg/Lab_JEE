<%-- 
    Document   : errorPage
    Created on : 16 paź 2024, 12:18:36
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Wprowadzono błędne dane!</h2>
        <p>Pojawił się następujący błąd: 
        <%= exception.getMessage() %>. <br />
        </p>
    </body>
</html>
