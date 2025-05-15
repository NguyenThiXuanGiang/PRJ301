
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
         <style>
            li {
                list-style: none;
            }
            a {
                text-decoration: none;
                color: #fff;
            }
            
        </style>
    </head>
    <body>
      <div id="panner" class="bg-info py-3">
            <ul class="d-flex align-items-center justify-content-center ">
                <li class="mx-5 fs-3">
                    <a href="orderManager"> < Back </a>
                </li>
                <li class="mx-5 fs-3">
                    <a href="logout">Logout</a>
                </li>
                <li  class="mx-5  fs-3">
                    Hello: ${sessionScope.admin.getUsername()}
                </li>
            </ul>
        </div>
        <h3 class="text-danger">${mess}</h3>
        <table border="1" class="fs-2 w-75 mx-auto mt-5 px-2">
            <thead class="rounded-lg">
                <tr class="text-white" style="background-color: rgb(0 149 122) !important;">
                    <th>item id</th>
                    <th>Img</th>
                    <th>product code</th>
                    <th>quantity</th>
                    <th>list_price</th>
                    <th>subtotal</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrderItem}" var="orderItem">
                <c:set var="pro" value="${orderItem.getProduct()}"/>
                    <tr class="border-bottom">
                        <td>${orderItem.item_id}</td>
                        <td>${pro.getProduct_id()}</td>
                        <td>
                            <img src="${pro.getImage()}" alt="alt"/>
                        </td>
                        <td>${orderItem.quantity}</td>
                        <td>${orderItem.list_price}</td>
                        <td>${orderItem.list_price * orderItem.quantity}</td>
                    </tr>
                    <c:set var="total" value="${total + orderItem.list_price * orderItem.quantity}" />
            </c:forEach>
            </tbody>
        </table>
        <h3 class="w-25 ms-auto text-success mt-3">Total bill: ${total}</h3>
        <script type="text/javascript">
            function changeStatus(param) {
                let url = "orderManager?Service=changeStatus&status=" + param[0].value + "&oid=" + param[1]
                window.location = url
            }
        </script>

    </body>
</html>