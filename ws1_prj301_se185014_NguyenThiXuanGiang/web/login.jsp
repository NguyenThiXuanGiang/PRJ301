<%-- 
    Document   : login
    Created on : Mar 1, 2025, 6:16:07 PM
    Author     : tamph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>      

        <style>
            /* Reset mặc định để giao diện nhất quán */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .login-container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 400px;
                text-align: center;
            }

            .login-container h1 {
                font-size: 28px;
                color: #333;
                margin-bottom: 20px;
            }

            .login-container h4 {
                color: #d9534f;
                font-size: 14px;
                margin-bottom: 15px;
                min-height: 20px; /* Đảm bảo khoảng trống ngay cả khi không có lỗi */
            }

            .login-container form {
                display: flex;
                flex-direction: column;
            }

            .login-container input[type="text"],
            .login-container input[type="password"] {
                width: 100%;
                padding: 12px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                outline: none;
                transition: border-color 0.3s;
            }

            .login-container input[type="text"]:focus,
            .login-container input[type="password"]:focus {
                border-color: #007bff;
            }

            .login-container input[type="text"]::placeholder,
            .login-container input[type="password"]::placeholder {
                color: #999;
            }

            .login-container input[type="submit"] {
                background-color: #007bff;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .login-container input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <%
                String error = (String) request.getAttribute("error");
                if (error == null) {
                    error = "";
                }
            %>          
            <h4><%= error%></h4>
            <h1>Login</h1>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login"/>
                <input type="text" name="username" placeholder="Username"/> <br/>
                <input type="password" name="password" placeholder="Password"/> <br/>
                <input type="submit" value="Login"/>
            </form>
        </div>
    </body>
</html>