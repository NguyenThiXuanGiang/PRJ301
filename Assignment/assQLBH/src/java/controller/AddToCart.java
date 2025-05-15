/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.OrderItemDAO;
import dal.ProductDAO;
import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Vector;

@WebServlet(name = "AddToCart", urlPatterns = {"/CartURL"})
public class AddToCart extends HttpServlet {

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
        String service = request.getParameter("Service");
        HttpSession session = request.getSession();
        ProductDAO dp = new ProductDAO();
        OrderDAO od = new OrderDAO();
        if (service == null) {
            //khoi tao
            int numOrder = 0;
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                //when get from session id is cart-id
                String id = em.nextElement().toString();
                if (id.startsWith("cart")) {

                    Product pro_session = (Product) session.getAttribute(id);
                    //tang len  neu start key là cart
                    numOrder ++;
                }
            }
            session.setAttribute("numberOrder", numOrder);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }
        
        if (service.equals("removeAll")) {
            java.util.Enumeration em = session.getAttributeNames();
                 while (em.hasMoreElements()) {
                //when get from session id is cart-id
                String id = em.nextElement().toString();
                if (id.startsWith("cart")) {
                      session.removeAttribute(id);
                }
            }
            response.sendRedirect("CartURL");
            return;
        }
        if (service.equals("addToCart")) {
            String pid_raw = request.getParameter("pid");
            String key = "cart-" + pid_raw;
            //san pham luu o dang key va value
            //get quantity add
            String quantity_raw = request.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_raw);

            //get product by key in session
            Product p = (Product) session.getAttribute(key);
            //IF DONT EXIST will store to the session a new product
            if (p == null) {
                Product p_root = dp.getProductById(pid_raw);
                Product pAdd = new Product(
                        pid_raw,
                        p_root.getName(),
                        quantity,
                        p_root.getPrice(),
                        p_root.getDescribe(),
                        p_root.getImage(),
                        p_root.getCategory()
                );

                session.setAttribute(key, pAdd);
            } else {
                //IF HAVE EXIST get old product in session and change quantity
                Product pAdd = new Product(
                        pid_raw,
                        p.getName(),
                        p.getQuantity() + quantity,
                        p.getPrice(),
                        p.getDescribe(),
                        p.getImage(),
                        p.getCategory()
                );
                session.setAttribute(key, pAdd);
            }
            response.sendRedirect("CartURL");
            return;
        }
        if (service.equals("add")) {
            String pid_raw = request.getParameter("pId");
            String key = "cart-" + pid_raw;
            //sp root
            Product p_root = dp.getProductById(pid_raw);
            Product p = (Product) session.getAttribute(key);
            int quantity = 0;
            //IF DONT EXIST
            if (p != null) {
                if ((p.getQuantity() + 1) == p_root.getQuantity()) {
                    quantity = p_root.getQuantity();
                } else {
                    quantity = p.getQuantity() + 1;
                }
                // create a new p and set in old key
                Product pAdd = new Product(
                        pid_raw,
                        p.getName(),
                        quantity,
                        p.getPrice(),
                        p.getDescribe(),
                        p.getImage(),
                        p.getCategory()
                );
                session.setAttribute(key, pAdd);
            }
            response.sendRedirect("CartURL");
        }

        if (service.equals("minus")) {
            String pid_raw = request.getParameter("pId");
            String key = "cart-" + pid_raw;
            Product p = (Product) session.getAttribute(key);
            //IF DONT EXIST
            if (p != null) {
                // create a new p and set in old key
                //check is delete
                if ((p.getQuantity() - 1) == 0) {
                    session.removeAttribute(key);
                } else {
                    Product pAdd = new Product(
                            pid_raw,
                            p.getName(),
                            p.getQuantity() - 1,
                            p.getPrice(),
                            p.getDescribe(),
                            p.getImage(),
                            p.getCategory()
                    );
                    session.setAttribute(key, pAdd);
                }
            }
            response.sendRedirect("CartURL");
        }
        if (service.equals("remove")) {
            String pid_raw = request.getParameter("pId");
            String key = "cart-" + pid_raw;
            session.removeAttribute(key);
            response.sendRedirect("CartURL");
        }
        if (service.equals("checkout")) {
            Vector<Product> listItem = new Vector<>();
            Customer cuss = (Customer) session.getAttribute("cuss");
            if (cuss != null) {
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    //when get from session id is cart-id
                    String id = em.nextElement().toString();
                    if (id.startsWith("cart")) {
                        Product pro_session = (Product) session.getAttribute(id);
                        listItem.add(pro_session);
                        session.removeAttribute(id);
                    }
                }
                if (listItem.size() > 0) {
                    od.checkcount(cuss, listItem);
                    response.sendRedirect("CartURL");
                }
            } else {
                response.sendRedirect("login");
            }
            return;
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
        processRequest(request, response);
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
        OrderDAO d = new OrderDAO();
        OrderItemDAO Oi = new OrderItemDAO();
//        Vector<OrderItem> list = don.getAllOrderItem();
        Vector<Order> list = d.getAllOrder();

        System.out.println(list.size());
    }
}
