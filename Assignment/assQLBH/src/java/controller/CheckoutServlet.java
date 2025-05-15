/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.OrderItemDAO;
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

/**
 *
 * @author Acer
 */
@WebServlet(name = "Checkout", urlPatterns = {"/CheckoutURL"})
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Customer cuss = (Customer) session.getAttribute("cuss"); // Get logged-in customer

        if (cuss == null) {
            // If no customer is logged in, redirect to login page
            response.sendRedirect("login");
            return;
        }

        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        // Get cart items from session
        Vector<Product> cartItems = new Vector<>();
        java.util.Enumeration em = session.getAttributeNames();

        double totalMoney = 0; // Total money of the order

        while (em.hasMoreElements()) {
            String id = em.nextElement().toString();
            if (id.startsWith("cart")) {
                Product product = (Product) session.getAttribute(id);
                cartItems.add(product);
                totalMoney += product.getPrice() * product.getQuantity(); // Calculate total money
            }
        }

        if (cartItems.isEmpty()) {
            // If cart is empty, redirect to cart page
            response.sendRedirect("CartURL");
            return;
        }

        // Create a new order
        // The order date can be retrieved dynamically in the format of "yyyy-MM-dd"
        String orderDate = java.time.LocalDate.now().toString(); // Get today's date
        Order newOrder = new Order(0, cuss.getCustomer_id(), "Processing", orderDate, totalMoney);

        // Insert the order into the database
        int orderId = orderDAO.insertOrder(newOrder); // Assuming insertOrder returns the generated order_id
        newOrder.setOrder_id(orderId); // Set the generated order_id for the new order

        // Save the order items into the database using checkcount method
        orderDAO.checkcount(cuss, cartItems);

        // Clear the cart session
        em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            String id = em.nextElement().toString();
            if (id.startsWith("cart")) {
                session.removeAttribute(id);
            }
        }

        // Sau khi xử lý đơn hàng và lưu vào cơ sở dữ liệu:
        request.setAttribute("order", newOrder); // newOrder là đối tượng Order bạn đã tạo trong servlet.
        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles the checkout process";
    }
}
