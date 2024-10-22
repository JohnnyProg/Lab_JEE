<%-- 
    Document   : details
    Created on : 22 paź 2024, 18:29:42
    Author     : jangr
--%>

<%@page import="com.mycompany.mavenproject1.lab3.CountryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            // Get the index from the URL parameter
            String indexParam = request.getParameter("index");
            if (indexParam != null) {
                int index = Integer.parseInt(indexParam);
                ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("countries");

                if (list != null && index >= 0 && index < list.size()) {
                    CountryBean country = list.get(index);
        %>

            <h1>Details for <%= country.getName() %></h1>
            <p>Code: <%= country.getCode() %></p>
            <p>Population: <%= country.getPopulation() %></p>
            <p>Surface area: <%= country.getSurfaceArea() %></p>
            <p><a href="ListServlet">Back to Country List</a></p>

        <%
                } else {
                    out.println("Country not found.");
                }
            } else {
                out.println("Invalid country index.");
            }
        %>
    </body>
</html>
