/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Exe;

/**
 *
 * @author HP
 */
@WebServlet(name="NewServlet", urlPatterns={"/show"})
public class NewServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet NewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String arr_str = request.getParameter("arr");
        String type = request.getParameter("type");
        
        request.setAttribute("arr", arr_str);
        request.setAttribute("type", type);
        
        //1,2,3,4,5 => [1, 2, 3, 4, 5]
        if (arr_str.isEmpty() || type.isEmpty()) {
            return;
        }
        String[] arr = arr_str.split(",");
        
        HttpSession session = request.getSession();
        List<Exe> list;
        if(session.getAttribute("list") == null) {
            list = new ArrayList<>();
        } else {
            list = (List<Exe>)session.getAttribute("list");
        }
        if(!list.isEmpty()) {
            for (Exe exe : list) {
                if(exe.getArray().equals(arr_str) && exe.getOption().equals(type)) {
                    request.setAttribute("mess", "Execution existed!");
                    request.getRequestDispatcher("MyExecution.jsp").forward(request, response);
                    return;
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = Integer.parseInt(arr[i]);
            if ((type.equals("odd") && num % 2 != 0) || (type.equals("even") && num % 2 == 0)) {
             count++;
            }
        }
        request.setAttribute("count", count);
        Exe exe = new Exe(arr_str, type, count+"");
        
        list.add(exe);
        session.setAttribute("list", list);
        request.getRequestDispatcher("MyExecution.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
