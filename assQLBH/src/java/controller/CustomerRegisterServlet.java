/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CustomerRegisterServlet", urlPatterns = {"/register"})
public class CustomerRegisterServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerRegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerRegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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

        String id = request.getParameter("rollnumber");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        CustomerDAO cdao = new CustomerDAO();
        Customer c = cdao.getCustomerById(id);
        Customer a = cdao.checkAccountExist(user);
        String err;
        if(id.isEmpty() || fname.isEmpty() || lname.isEmpty() ||
                phone.isEmpty() ||  email.isEmpty() || user.isEmpty()
                || pass.isEmpty() || re_pass.isEmpty()) {
            err = "please fill all of field";
             returnValue(request, response, "", fname, lname, phone, email, user, err);
          request.getRequestDispatcher("register.jsp").forward(request, response);
          return;
        }
        if(c != null) {
          err = "this id have exist";
          returnValue(request, response, "", fname, lname, phone, email, user, err);
          request.getRequestDispatcher("register.jsp").forward(request, response);
          return;
        }
        if(a != null) {
          err = "this username have exist";
          returnValue(request, response, id, fname, lname, phone, email, "", err);
          request.getRequestDispatcher("register.jsp").forward(request, response);
          return;
        }
        if(!pass.equals(re_pass)) {
         err = "confirm pass not equal new pass";
         returnValue(request, response, id, fname, lname, phone, email, user, err);
         request.getRequestDispatcher("register.jsp").forward(request, response);
         return;
        }
        Customer cNew = new Customer(id, fname, lname, phone, email, user, pass);
        cdao.insert(cNew);
        request.setAttribute("suscess", "You have successfully registered!!!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    void returnValue(HttpServletRequest request, HttpServletResponse response, 
            String id, String fname, String lname, String phone, String email,
            String user, String err) 
            throws ServletException, IOException {
      request.setAttribute("id", id);
      request.setAttribute("fname", fname);
      request.setAttribute("lname", lname);
      request.setAttribute("phone", phone);
      request.setAttribute("email", email);
      request.setAttribute("user", user);
      request.setAttribute("err", err);
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
