<%-- 
    Document   : welcome
    Created on : Mar 1, 2025, 6:50:57 PM
    Author     : tamph
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="java.util.List"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <style>
            /* Reset mặc định */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                padding: 20px;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            h1 {
                color: #333;
                font-size: 28px;
            }

            .logout-form input[type="submit"] {
                background-color: #d9534f;
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .logout-form input[type="submit"]:hover {
                background-color: #c9302c;
            }

            .search-form {
                margin-bottom: 20px;
                display: flex;
                gap: 10px;
            }

            .search-form input[type="text"] {
                padding: 8px;
                width: 300px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
            }

            .search-form input[type="submit"] {
                background-color: #5cb85c;
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .search-form input[type="submit"]:hover {
                background-color: #4cae4c;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            thead th {
                background-color: #007bff;
                color: white;
                padding: 12px;
                text-align: left;
            }

            tbody td {
                padding: 12px;
                border-bottom: 1px solid #ddd;
            }

            tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tbody tr:hover {
                background-color: #f1f1f1;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            .add-link {
                display: inline-block;
                background-color: #5cb85c;
                color: white;
                padding: 10px 20px;
                border-radius: 4px;
                text-decoration: none;
                transition: background-color 0.3s;
            }

            .add-link:hover {
                background-color: #4cae4c;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            String searchTerm = (String) request.getAttribute("searchTerm");
            searchTerm = (searchTerm != null && !searchTerm.equals("null")) ? searchTerm : "";
        %>
        <div class="container">
            <div class="header">
                <h1>Welcome <%= user.getName()%></h1>
                <form action="MainController" class="logout-form">
                    <input type="hidden" name="action" value="logout"/>
                    <input type="submit" value="Logout"/>
                </form>
            </div>

            <%
                if (user != null && user.getRole().equals("Founder")) {
            %>
            <form action="MainController" method="get" class="search-form">
                <input type="hidden" name="action" value="search"/>
                <input type="text" name="searchTerm" placeholder="Nhập từ khóa..." value="<%= searchTerm%>"/>
                <input type="submit" value="Tìm kiếm"/>
            </form>
            <% } %>

            <%
                List<ProjectDTO> p = (List<ProjectDTO>) request.getAttribute("list");
            %>

            <table>
                <thead>
                    <tr>
                        <th>ProjectID</th>
                        <th>ProjectName</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch</th>
                            <%
                                if (AuthUtils.isAdmin(session)) {
                            %>
                        <th>Action</th>
                            <% } %>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (p != null) {
                            for (ProjectDTO a : p) {
                    %>
                    <tr>
                        <td><%= a.getProject_id()%></td>
                        <td><%= a.getProject_name()%></td>
                        <td><%= a.getDescription()%></td>
                        <td><%= a.getStatus()%></td>
                        <td><%= a.getEstimated_launch()%></td>
                        <%
                            if (AuthUtils.isAdmin(session)) {
                        %>
                        <td> 
                            <a href="MainController?action=delete&id=<%= a.getProject_id()%>">Delete</a>
                            <a href="update.jsp?id=<%= a.getProject_id()%>">Update</a>
                        </td>
                        <% } %>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <%
                if (AuthUtils.isAdmin(session)) {
            %>
            <a href="projectForm.jsp" class="add-link">Add New Project</a>
            <%
                }
            %>

        </div>
    </body>
</html>