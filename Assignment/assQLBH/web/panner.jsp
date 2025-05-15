
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/panner.css"/>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            li {
                list-style: none
            }
        </style>
    </head>
    <body>
        <div class="content">
            <c:set var="c" value="${sessionScope.cuss}"/>
            <!-- Truoc khi login -->
            <c:if test="${(c == null)}">
                <div class="panner row">
                    <ul class="menu col-lg-6">
                        <li><a class="tw" href="./home?cid=0">Home</a></li> 
                        <li><a class="tw" href="./login">Login</a></li> 
                        <li><a class="tw" href="register.jsp">Register</a></li>
                    </ul>
                    
                        <ul class="sub_menu col-lg-6">
                            <li>
                                <a class="text-danger text-decoration-none" href="CartURL">
                                    <i class='bx bx-shopping-bag fs-1'></i>
                                    <span class="text-success">${sessionScope.numberOrder}</span>
                                </a>
                            </li>
                            <li>
                                <form action="home">
                                  <input name="Service" value="search" hidden/>
                                  <input type="text" class="search_box" 
                                       placeholder="&nbsp;&nbsp;Search..." 
                                       name="search_box" value="${searchvl}">
                                  <input type="submit" class="search_submit" value="SEARCH">
                                </form>
                            </li> 
                        </ul>
                    
                </div>
            </c:if>
            <!-- Sau khi login -->
            <c:if test="${(c != null)}">
                <div class="panner row">
                    <ul class="menu col-lg-6 d-flex align-items-center justify-content-between">
                        <li><a href="./home?cid=0">Home</a></li>
                        <li>
                            <span>NumberId: </span><span class="t_upcase">${c.customer_id}</span>
                        </li>
                        <li>
                            <span>Welcome: </span><span>${c.last_name}</span>
                        </li>
                        <li><a href="./logout">Logout</a></li>
                    </ul>
                    <form action="home">
                        <ul class="d-flex align-items-center mt-4">
                            <li class="fs-3">
                                <a class="text-danger text-decoration-none mx-5" href="CartURL">Show Cart ${size}</a>
                            </li>
                            <li>
                                <input type="text" name="Service" value="search" hidden>
                                <input type="text" class="search_box fs-3 py-2 px-3" 
                                       placeholder="&nbsp;&nbsp;Search..." 
                                       name="search_box">
                                <input type="submit" class="search_submit fs-3 py-2 px-3" value="SEARCH">
                            </li> 
                        </ul>
                    </form>
                </c:if>
            </div>
        </div> 
    </body>
</html>
