<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="assets/login.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <style>
            a {
                text-decoration: none;
            }
            .login-form {
                max-width: 400px;
                margin: 50px auto;
            }
            .login-form input[type="text"],
            .login-form input[type="password"] {
                width: 100%;
                margin-bottom: 10px;
                padding: 10px;
                font-size: 1rem;
            }
            .login-form input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #17a2b8;
                border: none;
                color: white;
                font-size: 1.25rem;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .login-form input[type="submit"]:hover {
                background-color: #138496;
            }
            .login-form .options {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div id="panner" class="bg-info py-3 fs-3">
            <a href="home" class="fs-3 text-center text-white">Home</a>
        </div>
        
        <div class="container">
            <div class="login-form">
                <h1>Login to Website</h1>
                <h3>${requestScope.error}</h3>
                <h3>${requestScope.success}</h3>
                <form action="login" method="post">
                    <input placeholder="&nbsp;&nbsp;Input Username" type="text" name="user" required/><br/>
                    <input placeholder="&nbsp;&nbsp;Input Password" type="password" name="pass" required/><br/>
                    
                    <!-- Remember Me Checkbox -->
                    <div class="options">
                        <label>
                            <input type="checkbox" name="rememberMe"> Remember Me
                        </label>
                        <a href="forgotPassword">Forgot Password?</a>
                    </div>
                    
                    <input type="submit" class="login_submit" value="Login"/>
                </form>
            </div>
        </div>
    </body>
</html>
