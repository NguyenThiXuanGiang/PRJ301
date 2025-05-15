<%-- 
    Document   : projectForm
    Created on : Mar 3, 2025, 9:29:06 AM
    Author     : tamph
--%>

<%@page import="dto.ProjectDTO"%>
<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Project</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
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
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="text"], input[type="date"], textarea, select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 14px;
            }
            textarea {
                resize: vertical;
            }
            .error-message {
                color: #d9534f;
                font-size: 12px;
                margin-top: 5px;
            }
            input[type="submit"] {
                background-color: #5cb85c;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
            }
            input[type="submit"]:hover {
                background-color: #4cae4c;
            }
            .error-page {
                text-align: center;
                padding: 50px;
            }
            .error-page a {
                color: #337ab7;
                text-decoration: none;
            }
            .error-page a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <% if (AuthUtils.isAdmin(session)) { %>
        <div class="container">
            <h1>Create New Project</h1>
            <%
                ProjectDTO project = new ProjectDTO();
                try {
                    project = (ProjectDTO) request.getAttribute("project");
                } catch (Exception e) {
                    project = new ProjectDTO();
                }
                if (project == null) {
                    project = new ProjectDTO();
                }
                String nameError = (String) request.getAttribute("name_error");
                if (nameError == null) {
                    nameError = "";
                }
                String descError = (String) request.getAttribute("desc_error");
                if (descError == null) {
                    descError = "";
                }
                String projectName = project.getProject_name();
                if (projectName == null) {
                    projectName = "";
                }
            %>
            <form action="MainController">
                <input type="hidden" name="action" value="create"/>

                <div class="form-group">
                    <label for="projectName">Project Name:</label>
                    <input type="text" id="projectName" name="projectName" value="<%= projectName%>"/>
                    <% if (!nameError.isEmpty()) {%>
                    <div class="error-message"><%= nameError%></div>
                    <% }%>
                </div>
                <div class="form-group">
                    <label for="desc">Description:</label>
                    <textarea rows="3" cols="20" id="desc" name="desc" placeholder="Enter description here"><%= project.getDescription() != null ? project.getDescription() : ""%></textarea>
                    <% if (!descError.isEmpty()) {%>
                    <div class="error-message"><%= descError%></div>
                    <% } %>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status">
                        <option value="Ideation">Ideation</option>
                        <option value="Development">Development</option>
                        <option value="Scaling">Scaling</option>
                        <option value="Launch">Launch</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="estimated_launch">Estimated Launch:</label>
                    <input type="date" id="estimated_launch" name="estimated_launch"/>
                </div>
                <input type="submit" value="Create"/>
            </form>
        </div>
        <% } else { %>
        <div class="error-page">
            <h1>404 Error</h1>
            <p>You do not have permission to access this page.</p>
            <a href="login.jsp">Back to Login</a>
        </div>
        <% }%>
    </body>
</html>