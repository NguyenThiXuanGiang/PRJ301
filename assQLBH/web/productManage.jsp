<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            li {
                list-style: none;
            }
            .navbar-custom {
                background-color: #17a2b8;
                padding: 15px;
            }
            .navbar-custom ul {
                margin: 0;
                padding: 0;
                list-style-type: none;
                display: flex;
                justify-content: center;
            }
            .navbar-custom li {
                margin: 0 15px;
            }
            .navbar-custom a {
                color: #fff;
                font-size: 1.25rem;
                text-decoration: none;
            }
            .content-wrapper {
                margin-top: 50px;
                display: flex;
                justify-content: center;
            }
            .btn-custom {
                background-color: #28a745;
                color: white;
                border-radius: 15px;
                padding: 10px 20px;
                font-size: 1.25rem;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }
            .btn-custom:hover {
                background-color: #218838;
            }
            table {
                width: 75%;
                margin: 50px auto;
                border-collapse: collapse;
            }
            table th, table td {
                padding: 10px;
                text-align: center;
            }
            table th {
                background-color: #00957a;
                color: white;
            }
            .alert-custom {
                font-size: 1.25rem;
                text-align: center;
                width: 75%;
                margin: 20px auto;
            }
        </style>
    </head>
    <body>
        <div id="panner" class="navbar-custom">
            <ul>
                <li>
                    <a href="logout">Logout</a>
                </li>
                <li>
                    Hello: ${sessionScope.admin.getUsername()}
                </li>
                <li>
                    <form action="productManage" class="d-inline-block">
                        <input type="text" name="action" value="search" hidden />
                        <input type="text" name="searchTxt" class="form-control d-inline-block w-auto" placeholder="Search..." />
                        <input type="submit" value="SEARCH" class="btn btn-outline-light" />
                    </form>
                </li>
            </ul>
        </div>
        
        <div class="content-wrapper">
            <ul class="d-flex justify-content-around w-50">
                <li>
                    <a href="productManage" class="btn-custom">Get All</a>
                </li>
                <li>
                    <a href="orderManager" class="btn-custom">Order Manage</a>
                </li>
                <li>
                    <a href="productManage?action=insert" class="btn-custom">Insert Product</a>
                </li>
            </ul>
        </div>

        <c:if test="${result != null}">
            <div class="alert alert-custom ${result == true ? 'alert-success' : 'alert-danger'}">
                ${mess}
            </div>
        </c:if>

        <c:set var="total" value="0" />
        <c:set var="count" value="0" />
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Stock</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listProd}" var="pro">
                    <c:set var="count" value="${count + 1}" />
                    <tr>
                        <td>${pro.getProduct_id()}</td>
                        <td><img src="${pro.getImage()}" alt="Product Image" style="width: 100px; height: 100px;" /></td>
                        <td>${pro.getName()}</td>
                        <td>${pro.getQuantity()}</td>
                        <td>${pro.getPrice()}</td>
                        <td>${pro.getCategory().getName()}</td>
                        <td>
                            <a href="productManage?action=update&product_id=${pro.getProduct_id()}" class="btn btn-primary btn-sm">Update</a>
                            <a href="#" onclick="confirmDelete('${pro.getProduct_id()}')" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="text-center fs-4">
            Total Products: ${count}
        </div>

        <script type="text/javascript">
            function confirmDelete(productId) {
                if (confirm("Có chắc muốn xóa sản phẩm không?")) {
                    window.location.href = `productManage?action=delete&product_id=${productId}`;
                }
            }
        </script>
    </body>
</html>
