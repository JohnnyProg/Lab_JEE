<%-- 
    Document   : countryList
    Created on : 22 paź 2024, 18:16:56
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
            ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("countries");
        %>
            <table border="1">
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Population</th>
                    <th>Details</th>
                </tr>
                <% for (CountryBean country : list) { %>
                    <tr>
                        <td><%= country.getCode() %></td>
                        <td><%= country.getName() %></td>
                        <td><%= country.getPopulation() %></td>
                        <td><a href="details.jsp?index=<%= list.indexOf(country) %>">Details</a></td>
                    </tr>
                <% } %>
            </table>

    </body>
</html>
