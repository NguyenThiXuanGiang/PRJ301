/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Vector;


@WebServlet(name="ProductManage", urlPatterns={"/productManage"})
public class ProductManage extends HttpServlet {
   ProductDAO pDao = new ProductDAO();
   CategoryDAO cateDao = new CategoryDAO();
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
            out.println("<title>Servlet ProductManage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManage at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        if(session.getAttribute("admin") == null) {
          response.sendRedirect("login");
          return;
        }
        Vector<Product> listProd = new Vector<>();
        String action = request.getParameter("action");
        if(action == null) {
         listProd = pDao.getAll();
         request.setAttribute("listProd", listProd);
         request.getRequestDispatcher("productManage.jsp").forward(request, response);
         return;
        }
        if(action.equals("insert")) {
          Vector<Category> listCate = cateDao.getAll();
          request.setAttribute("listCate", listCate);
          request.getRequestDispatcher("productInsert.jsp").forward(request, response);
        }
        if(action.equals("update")) {
          String product_id = request.getParameter("product_id");
          Product prod = pDao.getProductById(product_id);
          Vector<Category> listCate = cateDao.getAll();
          request.setAttribute("listCate", listCate);
          request.setAttribute("prod", prod);
          request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
        }
        if(action.equals("delete")) {
          String product_id = request.getParameter("product_id");
            boolean hasDelete = pDao.deleteProductById(product_id);
            String mess = "";
            request.setAttribute("result", hasDelete);
            if (hasDelete) {
                mess = "delete success";
            } else {
                mess = "delete error";
            }
            request.setAttribute("mess", mess);
            request.setAttribute("result", hasDelete);
            request.setAttribute("listProd", pDao.getAll());
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }
        if (action.equals("search")) {
            String searchTxt = request.getParameter("searchTxt");
            request.setAttribute("listProd", pDao.searchProducts(searchTxt));
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }
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
        String action = request.getParameter("action");
        ProductDAO pDao = new ProductDAO();
        String image = request.getParameter("image");
            String name = request.getParameter("name");
            String product_id = request.getParameter("product_id");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            String describe = request.getParameter("describe");
            int cid = Integer.parseInt(request.getParameter("category"));
            String mess;
            //is empty
            if (product_id.isEmpty() || name.isEmpty()
                    || request.getParameter("quantity").isEmpty()
                    || request.getParameter("price").isEmpty()
                    || describe.isEmpty() || request.getParameter("category").isEmpty()
                    || image.isEmpty()) {
                mess = "Please fill full field";
                setCommonInsert(request, response, image, name, product_id, quantity, price,
                        describe, cid, mess, false);
                if (action.equals("insert")) {
                request.getRequestDispatcher("productInsert.jsp").forward(request, response);
                } else {
                    Product proUp = pDao.getProductById(product_id);
                    request.setAttribute("prod", proUp);
                request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
                }
                return;
            }
        if (action.equals("insert")) {
            //is exst code
            if (pDao.getProductById(product_id) != null) {
                mess = "Have exist Code";
                setCommonInsert(request, response, image, name, "", quantity, price,
                        describe, cid, mess, false);
                request.getRequestDispatcher("productInsert.jsp").forward(request, response);
                return;
            }
            //validate success and insert
            Product prod = new Product(product_id, name, quantity,
                    price, describe, cid, image);
            prod.setImage("images/" + image);
            boolean hasInsert = pDao.insertProduct(prod);
            request.setAttribute("result", hasInsert);
            if (hasInsert) {
                mess = "insert success";
            } else {
                mess = "insert error";
            }
            setCommonInsert(request, response, image, name, product_id, quantity, price,
                    describe, cid, mess, hasInsert);
            request.getRequestDispatcher("productInsert.jsp").forward(request, response);
            return;
        }
        if (action.equals("update")) {
            //is exst code
            //validate success and insert
            Product prod = new Product(product_id, name, quantity,
                    price, describe, cid, image);
            if(!prod.getImage().startsWith("images/")) {
              prod.setImage("images/" + image);
            }
            boolean hasUpdate = pDao.updateProduct(prod);
            request.setAttribute("result", hasUpdate);
            if (hasUpdate) {
                mess = "update success";
            } else {
                mess = "update error";
            }
            setCommonInsert(request, response, image, name, product_id, quantity, price,
                    describe, cid, mess, hasUpdate);
            request.setAttribute("prod", pDao.getProductById(product_id));
            request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
        }
    }
    
    public void setCommonInsert(HttpServletRequest request, HttpServletResponse response,
            String image, String name, 
            String product_id, int quantity,
            float price, String describe, int cid, String mess, boolean hasInsert)
    throws ServletException, IOException {
      request.setAttribute("name", name);
      request.setAttribute("product_id", product_id);
      request.setAttribute("quantity", quantity);
      request.setAttribute("price", price);
      request.setAttribute("describe", describe);
      request.setAttribute("cid", cid);
      request.setAttribute("mess", mess);
      request.setAttribute("result", hasInsert);
      request.setAttribute("listCate", cateDao.getAll());
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
public static void main(String[] args) {
        ProductDAO pDao = new ProductDAO();
        System.err.println(pDao.deleteProductById("w_ultra2"));
    }
}
