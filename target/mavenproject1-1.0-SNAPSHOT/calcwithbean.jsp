<%-- 
    Document   : calc2
    Created on : 16 paź 2024, 11:46:38
    Author     : student
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<jsp:useBean id="loan" class="com.mycompany.mavenproject1.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%= new Date() %> 
        <h1>Hello World!</h1>
        
        <%
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String date = dateFormat.format(now);
        %> 
        <%=date%>
        <h1>Kalkulator rat</h1>
        <form action="calcwithbean.jsp" method="POST">
            <label for="kwota">Kwota pozyczki</label>
            <input name="kwota"kwota id="kwota"  /><!-- comment -->
            <label for="oprocentowanie">Oprocentowanie roczne</label>
            <input name="oprocentowanie" id="oprocentowanie" type="number"/><!-- comment -->
            <label for="nrat">Liczba rat</label>
            <input id="nrat" type="number" name="nrat"/><!-- comment -->
            <button type="submit" name="wyslij">Oblicz</button>
        </form>
        <% DecimalFormat format = new DecimalFormat("####.##");%>
        <%= format.format(loan.getRata()) %>

        
    </body>
</html>
