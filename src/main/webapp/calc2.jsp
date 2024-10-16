<%-- 
    Document   : calc2
    Created on : 16 paź 2024, 11:46:38
    Author     : student
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form action="calc2.jsp" method="POST">
            <label for="kwota">Kwota pozyczki</label>
            <input name="kwota"kwota id="kwota" type="number"/><!-- comment -->
            <label for="oprocentowanie">Oprocentowanie roczne</label>
            <input name="oprocentowanie" id="oprocentowanie" type="number"/><!-- comment -->
            <label for="nrat">Liczba rat</label>
            <input id="nrat" type="number" name="nrat"/><!-- comment -->
            <button type="submit" name="wyslij">Oblicz</button>
        </form>
        
        <% 
            double res = 0.0;
            if (request.getParameter("wyslij")!=null){
            try {
                double k = Double.parseDouble(request.getParameter("kwota"));
                double pr = Double.parseDouble(request.getParameter("oprocentowanie"));
                double n = Double.parseDouble(request.getParameter("nrat"));
                double p = pr / 12;
                res = (k*(p/12)) / (1 - (1 / Math.pow((1 + p), n)));
            }
            catch (Exception exc) {out.println(exc); }
                
            }
       %>
       <%=res%>
        
    </body>
</html>
