/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class Operation {
    public double n1;
    public double n2;
    public double wynik;
    public String operator;
    
    public Operation(double n1, double n2, double wynik, String operator) {
        this.n1 = n1;
        this.n2 = n2;
        this.wynik = wynik;
        this.operator = operator;
    }
}

/**
 *
 * @author student
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalcServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private boolean checkParams(String param1, String param2,  PrintWriter out) {
        
        if ((param1 == null) || (param1.trim().equals(""))) {
            out.println("Param 1 missing");
            return false;
        }
        if ((param2 == null) || (param2.trim().equals(""))) {
            out.println("Param 2 missing");
            return false;
        }
        
        return true;
    }
    
    private void saveOperationToSession(HttpServletRequest request, Operation operation) {
        HttpSession sesja = request.getSession(true);
        ArrayList<Operation> history;
        if(sesja.getAttribute("history") == null) {
            history = new ArrayList<Operation>();
            
        }else {
            history = (ArrayList<Operation>) sesja.getAttribute("history");
        }
        history.add(operation);
        sesja.setAttribute("history", history);
    }
    
    private ArrayList<Operation> getHistoryOfOperations(HttpServletRequest request) {
        HttpSession sesja = request.getSession(true);
        ArrayList<Operation> history;
        if(sesja.getAttribute("history") == null) {
            history = new ArrayList<Operation>(); 
        }else {
            history = (ArrayList<Operation>) sesja.getAttribute("history");
        }
        return history;
    }
    
    private boolean clearHistory(HttpServletRequest request) {
        if(request.getParameter("clear") != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("history", null);
            return true;
        }
        return false;
    }
    
    private boolean deleteFromHistory(HttpServletRequest request, PrintWriter out) {
        out.println("TEST!@#");
        if(request.getParameter("clearId") != null) {
            out.print("TEST!@#");
            HttpSession session = request.getSession(true);
            String s =  request.getParameter("clearId");
            out.print("id = " + s);
            int id = Integer.parseInt(s);
            out.print("id = " + id);
            ArrayList<Operation> history = (ArrayList<Operation>) session.getAttribute("history");
            history.remove(id);
            out.print("removed from history");
            session.setAttribute("history", history);
            return true;
        }
        return false;
    }
    
    private void oblicz(HttpServletRequest request, HttpServletResponse response, boolean isGet) throws IOException {
        Map<String, String[]> params = request.getParameterMap();
        String param1 = request.getParameter("num1");
        String param2 = request.getParameter("num2");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html");
            String nazwaCookie = "visited";
            Cookie[] cookies = request.getCookies();
            boolean foundUser = false;
            if ( cookies != null ){ 
                for (int i=0; i<cookies.length; i++) {
                    Cookie c=cookies[i];
                    if (nazwaCookie.equals(c.getName())) {
                        out.println("<h1>Witaj po raz kolejny</h1><br>");
                        foundUser = true;
                    }
                }
            }   
            if(!foundUser) {
                Cookie c = new Cookie(nazwaCookie, "1");
                response.addCookie(c);
                out.println("<h1>Witaj po raz pierwszy</h1><br>");
            }
            
            
            
            if(clearHistory(request)) {
                out.print("History Cleared");
                return;
            }
            if(deleteFromHistory(request, out)) {
                out.print("deleted from history");
                return;
            }
            if(isGet) {
                ArrayList<Operation> history = getHistoryOfOperations(request);
                out.println("Historia: + <br>");
                int counter = 0;
                for( Operation operationx : history) {

                    out.println( operationx.n1 + " " + operationx.operator +  " " + operationx.n2 + " = " + operationx.wynik + "   ");
                    out.println("   <a href='CalcServlet?clearId=" +counter + "'>Clear History</a>"  + "<br>");
                    counter++;
                }
                out.println("   <a href='CalcServlet?clear=TRUE'>Clear History</a>");
                return;
            }
            if (!checkParams(param1, param2, out)) {
                out.println("Costam costam");
                return;
            }

            double num1 = Double.valueOf(param1);
            double num2 = Double.valueOf(param2);

            if(num2 == 0) {
                out.println("Cannot divide by 0");
                return;
            }

            String operator = params.get("operation")[0];
            double wynik = this.selector(operator, num1, num2);
            

            out.println(num1 + " " + operator + " " + num2 + " = " + wynik + "<br>");
            
            Operation operation = new Operation(num1, num2, wynik, operator);
            saveOperationToSession(request, operation);
            ArrayList<Operation> history = getHistoryOfOperations(request);
            out.println("Historia: + <br>");
            int counter = 0;
            for( Operation operationx : history) {
                
                out.println( operationx.n1 + " " + operationx.operator +  " " + operationx.n2 + " = " + operationx.wynik + "   ");
                out.println("   <a href='CalcServlet?clearId=" +counter + "'>Clear History</a>"  + "<br>");
                counter++;
            }
            out.println("   <a href='CalcServlet?clear=TRUE'>Clear History</a>");
        }
        
       
    }
    
    private double selector(String operator, double a, double b) {
     switch(operator) {   
         case "+":
             return a + b;
         case "-":
             return a - b;
         case "*":
             return a * b;
         case "/":
             return a / b;
     }
     return 0;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        oblicz(request, response, true);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            oblicz(request, response, false);
//processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
