/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.OrderItemDAO;
import dal.ProductDAO;
import entity.Category;
import entity.OrderItem;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;


@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO ctdao = new CategoryDAO();
        Vector<Category> list = ctdao.getAll();
        ProductDAO pdao = new ProductDAO();
        OrderItemDAO oiDao = new OrderItemDAO();
        Vector<Product> products = pdao.getAll();
        String service = request.getParameter("Service");
        if(service == null) {
        request.setAttribute("category", list);
        request.setAttribute("products", products);
         request.setAttribute("topProduct", pdao.getProductById(oiDao.getTopOrder()));
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
        }
        if(service.equals("filter")) {
          String cid_raw = request.getParameter("cid");
          if (cid_raw != null) {
            products = pdao.getProductByCid(Integer.parseInt(cid_raw));
            } else {
            products = pdao.getProductsByName("");
          }
        }
        
        if(service.equals("search")) {
            String search = request.getParameter("search_box");
            products = pdao.getProductsByName(search);
            request.setAttribute("searchvl", search);
        }
        request.setAttribute("category", list);
        request.setAttribute("topProduct", pdao.getProductById(oiDao.getTopOrder()));
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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
public static void main(String[] args) {
    ProductDAO pdao = new ProductDAO();
    System.out.println(pdao.getProductById("air15m2_256gb"));
        
    }
}
