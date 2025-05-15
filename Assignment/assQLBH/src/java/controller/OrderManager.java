/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.OrderItemDAO;
import entity.Order;
import entity.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Vector;


@WebServlet(name="OrderManager", urlPatterns={"/orderManager"})
public class OrderManager extends HttpServlet {
   
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
       String service = request.getParameter("Service");
       HttpSession session = request.getSession();
       if(session.getAttribute("admin") == null) {
          response.sendRedirect("login");
          return;
        }
        OrderDAO od = new OrderDAO();
        Vector<Order> list = new Vector<>();
        if (service == null) {
            list = od.getAllOrder();
            request.setAttribute("listStatus", listStatus());
            request.setAttribute("list", list);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
            return;
        }
        if(service.equals("changeStatus")) {
            String orderid_raw = request.getParameter("oid");
            String status_raw = request.getParameter("status");
            int order_Id = Integer.parseInt(orderid_raw);
            od.updateStatus(status_raw, order_Id);
            response.sendRedirect("orderManager");
        }
        if(service.equals("search")) {
            String searchTxt = request.getParameter("searchTxt");
            list = od.searchOrder(searchTxt);
            request.setAttribute("listStatus", listStatus());
            request.setAttribute("list", list);
            request.setAttribute("searchTxt", searchTxt);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        }
         if(service.equals("detailOrder")) {
             OrderItemDAO oid = new OrderItemDAO();
             String orderid_raw = request.getParameter("oid");
             int od_id = Integer.parseInt(orderid_raw);
             Vector<OrderItem> listOrderItem = oid.getAllOrderItemByOid(od_id);
             request.setAttribute("listOrderItem", listOrderItem);
             request.getRequestDispatcher("detailOrder.jsp").forward(request, response);
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
        processRequest(request, response);
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

    public ArrayList<String> listStatus() {
     ArrayList<String> listStatus = new ArrayList<String>();
            listStatus.add("wait");
            listStatus.add("process");
            listStatus.add("done");
            return listStatus;
    }
    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        Order od = o.getOrderById(16);
        System.out.println(od);
    }
}
