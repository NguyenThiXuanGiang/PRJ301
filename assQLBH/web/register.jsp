<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register to Website</title>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 50px;
            }
            .register {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            .register h1 {
                color: #343a40;
                margin-bottom: 30px;
            }
            .register h3 {
                color: #dc3545;
            }
            .register input {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ced4da;
                border-radius: 5px;
            }
            .register_submit {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #28a745;
                color: #ffffff;
                font-size: 18px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .register_submit:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div id="panner">
            <jsp:include page="panner.jsp"/>
        </div>
        
        <div class="container">
            <div class="register">
                <h1>Register to Website</h1>
                <h3>${requestScope.err}</h3>
                <h3>${requestScope.errorpass}</h3>
                <h3>${requestScope.erroruser}</h3>
                <h3>${requestScope.errorid}</h3>
                <form action="register" method="post">
                    <input placeholder="Enter Roll Number" type="text" value="${id}" name="rollnumber"/><br/>
                    <input placeholder="Enter First Name" type="text" value="${fname}" name="fname"/><br/>
                    <input placeholder="Enter Last Name" type="text"  value="${lname}" name="lname"/><br/>
                    <input placeholder="Enter Number Phone" type="text"  value="${phone}" name="phone"/><br/>
                    <input placeholder="Enter Email" type="email"  value="${email}" name="email"/><br/>
                    <input placeholder="Enter Username" type="text"  value="${user}" name="user"/><br/>
                    <input placeholder="Enter Password" type="password"  name="pass"/><br/>
                    <input placeholder="Confirm Password" type="password"  name="repass"/><br/>
                    <input type="submit" class="register_submit" value="Register"/>
                </form>
            </div>
        </div>
    </body>
</html>
