

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/panner.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
    </head>
    <body>
        <div class="content">
            <!-- Sau khi login -->
            <div class="panner row">
                <ul class="menu col-lg-6">
                    <c:set var="c" value="${sessionScope.customer}"/>
                    <li><span>RollNumber: </span><span class="t_upcase">${c.customer_id}</span></li>
                    <li><span>Welcome: </span><span>${c.last_name}</span></li>
                    <li><a class="tw" href="">Logout</a></li>
                </ul>
                <ul class="sub_menu col-lg-6">
                    <li><a class="tw cart" href="">Show Cart</a></li>
                    <li>
                        <input type="text" class="search_box" name="search_box">
                        <input type="submit" class="search_submit" value="SEARCH">
                    </li> 
                </ul>
            </div>
        </div>
    </body>
</html>
