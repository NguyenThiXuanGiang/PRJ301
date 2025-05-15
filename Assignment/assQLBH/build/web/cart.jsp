

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="dal.*" %>
<%@page import="entity.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="assets/content.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
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
        <section>
            <div class="container">
                <ul class="d-flex justify-content-between">
                    <li>
                        <a href="home" class="fs-2">/home</a>
                    </li>
                    <li>
                        <a href="myOrder" class="fs-2">/my order</a>
                    </li>
                    <li>
                        <a class="text-danger text-decoration-none" href="CartURL">
                            <i class='bx bx-shopping-bag fs-1'></i>
                            <span class="text-success fs-1">${sessionScope.numberOrder}</span>
                        </a>
                    </li>
                </ul>
                <div class="row border-top ">
                    <div class="col-md-12 row border-top py-3">
                        <%--<c:forEach items="${cart.items}" var="item" >--%>
                        <div class="col-md-12 pe-5">
                            <div class="row order-item py-5">
                                    <div class="row bg-black text-white rounded-md py-3">
                                            <div class="col-md-3 fs-3 fw-bold">Product image</div>
                                            <div class="col-md-2 fs-3 fw-bold">Name</div>
                                            <div class="col-md-1 fs-3 fw-bold">Discount</div>
                                            <div class="col-md-1 fs-3 fw-bold">Price</div>
                                            <div class="col-md-2 fs-3 fw-bold">Quantity</div>
                                            <div class="col-md-1 fs-3 fw-bold">Apply</div>
                                            <div class="col-md-2 fs-3 fw-bold">Setting</div>
                                    </div>
                                    <%
                                      Customer cuss = null;
                                     if(session.getAttribute("cuss") != null) {
                                         cuss = (Customer)session.getAttribute("cuss");
                                      }
                                      int numberOrder = 0;
                                      double totalBill = 0;
                                     
                                     java.util.Enumeration em = session.getAttributeNames();
                                     while(em.hasMoreElements()){
                                         //id la cart-id
                                         String id = em.nextElement().toString();
                                        if (id.startsWith("cart")) {
                                          numberOrder++;
                                          Product pro = (Product)session.getAttribute(id);
                                          totalBill += pro.getAfterPrice();
                                     %>
                                        <div class="row border mt-3 align-items-center rounded-sm">
                                            <div class="col-md-3">
                                                <img src="<%=pro.getImage()%>" alt="" style="width: 150px;" class="rounded-lg object-fit-cover">
                                            </div>
                                            <div class="col-md-2">
                                                <h3 class="fw-bold"><%=pro.getName()%></h3>
                                            </div>
                                            <div class="col-md-1 text-center">
                                                <span class="fs-4"><%=pro.getPrice()%> vnd</span>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="box-input" >
                                                    <div class="box-input d-flex">
                                                        <button class="border bg-white">
                                                            <a href="CartURL?Service=minus&pId=<%=pro.getProduct_id()%>" class="p-3 text-black"><i class="fa-solid fa-minus fs-5"></i></a>
                                                        </button>
                                                        
<input type="text" readonly value="<%=pro.getQuantity()%>" class="py-3 fs-3 w-25 border outline-0 text-center" id="numberValue" />
                                                        
                                                        <button class="border bg-white">
                                                            <a href="CartURL?Service=add&pId=<%=pro.getProduct_id()%>" class="p-3 text-black"><i class="fa-solid fa-plus fs-5"></i></a>
                                                        </button>
                                                   </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1 text-center">
                                                <span class="fs-4 ms-"><%=String.format("%,.0f",pro.getAfterPrice()).replace(",", ".")%> vnd</span>
                                            </div>
                                            <div class="col-md-2">
                                                <a href="CartURL?Service=remove&pId=<%=pro.getProduct_id()%>" 
                                                   class="text-decoration-none fs-4 fw-bold text-danger">
                                                    Remove
                                                </a>
                                            </div>
                                        </div>
                                        <%}%>
                                        <%}%>
                                    </div>
                            </div>
                        </div>

                    </div>
                                    <a href="CartURL?Service=removeAll" >Remove all</a>
                    <div class="col-md-12 text-order fs-4 fw-500 p-5">
                        <div class="w-80 m-auto">
                            <h4 class="fw-semibold text-dark">Order Summary</h4>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Total:</span>
                                <span><%=String.format("%,.0f",totalBill).replace(",", ".")%> vnd</span>
                            </div>
                            <a href="CheckoutURL" class="mt-5 d-block
                               py-3 text-decoration-none fs-2 text-center
                               w-25 text-white bg-dark rounded-xl box-shadow1">
                                Checkout
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
