<%-- 
    Document   : update.jsp
    Created on : Mar 4, 2025, 3:44:34 PM
    Author     : tamph
--%>

<%@page import="dao.ProjectDAO"%>
<%@page import="utils.AuthUtils"%>
<%@page import="dto.ProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Project</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                padding: 20px;
            }
            .container {
                max-width: 600px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            h1 {
                color: #333;
                text-align: center;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                font-weight: bold;
                color: #555;
            }
            select, input[type="submit"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
            }
            input[type="submit"] {
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
                       
        </style>
    </head>
    <body>
        <% if (AuthUtils.isAdmin(session)) { %>
        <div class="container">
            <h1>Update Project</h1>
            <%
                ProjectDAO pd = new ProjectDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                ProjectDTO p = pd.searchById(id);
            %>
            <form action="MainController">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%= p.getProject_id()%>"/>
                <div class="form-group">
                    <label>Project ID:</label>
                    <span><%= p.getProject_id()%></span>
                </div>
                <div class="form-group">
                    <label>Project Name:</label>
                    <span><%= p.getProject_name()%></span>
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <span><%= p.getDescription()%></span>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status">
                        <option value="Ideation" <%= p.getStatus().equals("Ideation") ? "selected" : ""%>>Ideation</option>
                        <option value="Development" <%= p.getStatus().equals("Development") ? "selected" : ""%>>Development</option>
                        <option value="Scaling" <%= p.getStatus().equals("Scaling") ? "selected" : ""%>>Scaling</option>
                        <option value="Launch" <%= p.getStatus().equals("Launch") ? "selected" : ""%>>Launch</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Estimated Launch:</label>
                    <span><%= p.getEstimated_launch()%></span>
                </div>
                <input type="submit" value="Update"/>
                <a href="MainController?action=search">Back to home<a/>
                    <%
                        String mess = (String)request.getAttribute("mess");
                        if(mess == null)
                            mess = "";
                        %>
                        <div><%= mess %><div/>
                        <%
                    %>
            </form>
        </div>
        <% }%>
    </body>
</html>