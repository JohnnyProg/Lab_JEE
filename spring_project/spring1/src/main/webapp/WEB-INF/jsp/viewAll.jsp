<%-- 
    Document   : viewAll
    Created on : 23 paź 2024, 12:28:31
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" 
           prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pracownicy</title>
    </head>
    <body>
        <div>
            <h1>Lista pracowników</h1>
            <table border>
                <tr> <th>Id</th> <th>Nazwisko</th> <th>Pensja</th> <th>Firma</th> 
                    <th>Edytuj</th> <th>Usuń</th>
                </tr>
                <c:forEach var="pr" items="${list}"> 
                    <tr>
                        <td> ${pr.id} </td>
                        <td> ${pr.nazwisko} </td>
                        <td> ${pr.pensja} </td>
                        <td> ${pr.firma} </td>
                        <td><a href="edit/${pr.id}"> Edytuj </a></td>
                        <td><a href="delete/${pr.id}"> Usuń </a></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <a href="addForm">Dodaj nowego pracownika</a>
        </div>
    </body>
</html>
