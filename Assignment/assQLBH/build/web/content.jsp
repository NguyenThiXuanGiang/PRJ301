

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/content.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <div class="content_product">
            <h1>Product</h1>
            <div class="row product_list">
                <ul>
                <!--<form action="cart">-->
                    <c:forEach items="${requestScope.products}" var="p">
                        <li>
                            <form action="CartURL">
                                <input name="Service" value="addToCart" hidden/>
                                <input name="pid" value="${p.product_id}" hidden/>
                                <img  src="${p.image}" alt=""/>
                                <p>${p.name}</p>
                                <p class="">Price: <span class="old">${p.price}$</span></p>
                                <p class="product_price">Quantity <span>${p.quantity}</span></p>
                                <p>
                                    <input type="number" value="1" min="1" class="w-25" name="quantity" readonly="" >
                                    <input type="submit" class="addcart" value="Add to Cart"/>
                                </p>
                            </form>
                     </li>
                    </c:forEach>
                     
               <!--</form>-->
                </ul>
            </div>
        </div>
    </body>
</html>
