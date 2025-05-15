
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/menu.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
    </head>
    <body>
        <div class="category_menu">
            <h1>Category</h1>
            <ul class="category_list">
                <li><a class="" href="home?cid=${0}">All</a></li>
                <c:forEach items="${requestScope.category}" var="cate">
                <li><a class="" href="home?Service=filter&cid=${cate.category_id}">${cate.name}</a></li>
                </c:forEach>
            </ul>
                <ul>
                    <span class="fs-2 fw-bold">Top bán chạy nhất</span>  
                    <c:set value="${requestScope.topProduct}" var="proTop"/>
                    <li>
                        <form action="CartURL">
                            <input name="Service" value="addToCart" hidden/>
                            <input name="pid" value="${proTop.product_id}" hidden/>
                            <img  src="${proTop.image}" alt=""class="w-100 h-100"/>
                            <p>${proTop.name}</p>
                            <p class="">Price: <span class="old">${proTop.price*1.5}$</span></p>
                            <p class="product_price">Sale: <span>${proTop.price}$</span></p>
                            <p>
                                <input type="number" value="1" class="w-25" name="quantity" >
                                <input type="submit" class="addcart" value="Add to Cart"/>
                            </p>
                        </form>
                    </li>
                </ul>
        </div>
    </body>
</html>
