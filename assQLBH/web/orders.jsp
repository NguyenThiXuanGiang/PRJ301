<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                color: #333;
                margin: 0;
                padding: 0;
            }

            #panner {
                background-color: #17a2b8;
                padding: 15px 0;
            }

            #panner ul {
                padding: 0;
                margin: 0;
                list-style: none;
            }

            #panner ul li {
                margin: 0 30px;
                font-size: 1.5rem;
                color: #fff;
            }

            #panner ul li a {
                color: #fff;
                text-decoration: none;
                transition: color 0.3s ease;
            }

            #panner ul li a:hover {
                color: #c4e2f2;
            }

            .btn-custom {
                display: inline-block;
                padding: 10px 30px;
                background-color: #28a745;
                color: #fff;
                text-decoration: none;
                border-radius: 20px;
                font-size: 1.2rem;
                text-align: center;
                transition: background-color 0.3s ease;
            }

            .btn-custom:hover {
                background-color: #218838;
            }

            .table-container {
                margin-top: 50px;
                width: 80%;
                margin-left: auto;
                margin-right: auto;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                overflow: hidden;
            }

            table thead {
                background-color: #00957a;
                color: #fff;
            }

            table thead th {
                padding: 15px;
                text-align: left;
                font-size: 1.2rem;
            }

            table tbody tr {
                border-bottom: 1px solid #ddd;
            }

            table tbody tr:last-child {
                border-bottom: none;
            }

            table tbody td {
                padding: 15px;
                font-size: 1.1rem;
            }

            table tbody tr:hover {
                background-color: #f1f1f1;
            }

            table tbody a {
                color: #17a2b8;
                text-decoration: none;
                font-weight: bold;
            }

            table tbody a:hover {
                text-decoration: underline;
            }

            table tbody select {
                padding: 5px 10px;
                font-size: 1rem;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            h3 {
                text-align: center;
                margin-top: 20px;
                color: #00957a;
                font-size: 1.8rem;
                font-weight: bold;
            }

            .action-buttons {
                display: flex;
                justify-content: center;
                gap: 15px;
                margin-top: 40px;
            }

        </style>
    </head>
    <body>
        <div id="panner" class="bg-info py-3">
            <ul class="d-flex align-items-center justify-content-center">
                <li class="mx-5 fs-3">
                    <a href="logout">Logout</a>
                </li>
                <li class="mx-5 fs-3">
                    Hello: ${sessionScope.admin.getUsername()}
                </li>
                <li class="mx-5 fs-3">
                    <form action="orderManager" method="GET">
                        <input type="hidden" name="Service" value="search" />
                        <input type="text" name="searchTxt" value="${searchTxt}" class="form-control" placeholder="Search orders" />
                        <input type="submit" class="btn btn-primary mt-2" value="SEARCH" />
                    </form>
                </li>
            </ul>
        </div>

        <div class="action-buttons">
            <a href="orderManager" class="btn-custom">Get All</a>
            <a href="productManage" class="btn-custom">Product Manage</a>
        </div>

        <c:set var="total" value="0" />
        <c:set var="count" value="0" />

        <div class="table-container">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Total Money</th>
                        <th>Detail</th>
                        <th>Setting</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="order">
                        <tr>
                            <td>${order.order_id}</td>
                            <td>${order.customer_id}</td>
                            <td>${order.order_status}</td>
                            <td>${order.order_date}</td>
                            <td>${order.totalMoney}</td>
                            <c:set var="count" value="${count+1}" />
                            <c:set var="total" value="${total+order.totalMoney}" />
                            <td>
                                <a href="orderManager?Service=detailOrder&oid=${order.order_id}">Detail</a>
                            </td>
                            <td>
                                <select onchange="changeStatus([this, ${order.order_id}])" class="form-select">
                                    <c:forEach items="${listStatus}" var="status">
                                        <option value="${status}" ${order.order_status.equals(status) ? "selected" : ""}>${status}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <h3>Total: ${total}</h3>
        <h3>Have: ${count} bill</h3>

        <script>
            function changeStatus(param) {
                let url = "orderManager?Service=changeStatus&status=" + param[0].value + "&oid=" + param[1];
                window.location = url;
            }
        </script>
    </body>
</html>
