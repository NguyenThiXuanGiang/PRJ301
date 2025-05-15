<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="assets/bootstrap.min.css"/>
    <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body>
    <div class="container mt-5">
        <h2>Order Confirmation</h2>
        <p>Your order has been successfully placed! Below are the details of your order:</p>

        <!-- Order details -->
        <div class="order-details">
            <h3>Order Details</h3>
            <p><strong>Order ID:</strong> ${requestScope.order.order_id}</p>
            <p><strong>Customer:</strong> ${requestScope.order.customer_id}</p>
            <p><strong>Status:</strong> ${requestScope.order.order_status}</p>
            <p><strong>Order Date:</strong> ${requestScope.order.order_date}</p>
            <p><strong>Total Amount:</strong> ${requestScope.order.totalMoney} USD</p>
        </div>

        

        <p><strong>Grand Total:</strong> ${requestScope.order.totalMoney} USD</p>

        <div class="mt-3">
            <a href="home" class="btn btn-primary">Back to Home</a>
        </div>
    </div>

    <script src="assets/bootstrap.bundle.min.js"></script>
</body>
</html>
