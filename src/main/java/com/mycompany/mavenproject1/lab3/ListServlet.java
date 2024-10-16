/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.lab3;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet (name = "ListServlet", urlPatterns = {"/ListServlet"})
public class ListServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        //pobranie sterownika do MySQL:
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        //utworzenie obiektu połączenia do bazy danych MySQL:
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");
        //utworzenie obiektu do wykonywania zapytań do bd:
        Statement st = conn.createStatement(); 
        String query="SELECT * FROM Country WHERE Continent = 'Europe'";
        //wykonanie zapytania SQL:
        ResultSet rs = st.executeQuery(query);
        try (PrintWriter out = response.getWriter()) {
            while (rs.next()) {
                out.print(rs.getString("name"));   
            }
            
        }
        
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        processRequest(request, response);
            
        } catch(SQLException e) {
            
        }catch (ClassNotFoundException e) {
            
        }
    }
}
