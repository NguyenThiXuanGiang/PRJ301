/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author tamph
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private ProjectDAO pd = new ProjectDAO();

    public UserDTO getUser(String username) {
        UserDAO udao = new UserDAO();
        UserDTO udto = udao.readByUsername(username);
        return udto;
    }

    public boolean isValidUser(String username, String password) {
        UserDTO user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    public String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            String searchTerm = request.getParameter("searchTerm");

            if (searchTerm == null) {
                searchTerm = "";
            }

            List<ProjectDTO> list = pd.searchByName(searchTerm);
            request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("list", list);
            url = "welcome.jsp";
        }

        return url;
    }

    public String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isValidUser(username, password)) {
            url = "welcome.jsp";
            UserDTO user = getUser(username);
            request.getSession().setAttribute("user", user);
            List<ProjectDTO> p = pd.readAll();
            request.setAttribute("list", p);
        } else {
            String error = "Incorrect username or password";
            request.setAttribute("error", error);
            url = "login.jsp";
        }
        return url;
    }

    public String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            request.getSession().invalidate();
            url = "login.jsp";
        }
        return url;
    }

    public String processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            int id = Integer.parseInt(request.getParameter("id"));
            pd.setStatusToNone(id);
            url = processSearch(request, response);
        }
        return url;
    }

    public String processCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            try {
                boolean checkValid = true;
                request.setAttribute("name_error", "");
                request.setAttribute("desc_error", "");
                
                String projectName = request.getParameter("projectName");
                String desc = request.getParameter("desc");
                String status = request.getParameter("status");
                Date estimated_launch = Date.valueOf(request.getParameter("estimated_launch"));

                if (projectName == null || projectName.trim().isEmpty()) {
                    checkValid = false;
                    request.setAttribute("name_error", "ProjectName can not empty");
                }

                if (desc.trim().isEmpty()) {
                    checkValid = false;
                    request.setAttribute("desc_error", "Decription can not empty");
                }

                ProjectDTO pdto = new ProjectDTO(projectName, desc, status, estimated_launch);

                if (checkValid == true) {
                    pd.create(pdto);
                    url = processSearch(request, response);
                } else {
                    url = "projectForm.jsp";
                    request.setAttribute("project", pdto);
                }
            } catch (Exception e) {
            }
        }

        return url;
    }

    public String processUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ProjectDTO project = pd.searchById(id);
//            request.setAttribute("project", project);
//            request.getRequestDispatcher("update.jsp").forward(request, response);

            String status = request.getParameter("status");
            if(status == null){
                request.setAttribute("mess", "Update fail");
                url = "update.jsp";
            }else{
                pd.updateStatus(project, status);
                request.setAttribute("mess", "Update success");
                url = "update.jsp";
            }
                      
        }
        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");

            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("logout")) {
                    url = processLogout(request, response);
                } else if (action.equals("search")) {
                    url = processSearch(request, response);
                } else if (action.equals("delete")) {
                    url = processDelete(request, response);
                } else if (action.equals("create")) {
                    url = processCreate(request, response);
                } else if (action.equals("update")) {
                    url = processUpdate(request, response);
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

}
