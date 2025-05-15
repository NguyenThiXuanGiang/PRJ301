
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
        </style>
    </head>
    <body>
        <div id="panner" class="bg-info py-3">
            <ul class="d-flex align-items-center justify-content-center ">
                <li class="mx-5 fs-3">
                    <a href="logout">Logout</a>
                </li>
                <li  class="mx-5  fs-3">
                    Hello: ${sessionScope.admin.getUsername()}
                </li>
                <li  class="mx-5 fs-3">
                    <form action="orderManager">
                        <input type="text" name="Service" value="searchId" hidden />
                        <input type="text" name="oid" value="" />
                        <input type="submit"  value="SEARCH" />
                    </form>
                </li>
            </ul>
        </div>
        <div>
                    <a href="productManage" class="text-decoration-none ms-5 fs-3 text-info">< Back</a>
        </div>
        <c:if test="${result!=null}">
            <div class="fs-4 alert ${result==true?"alert-success":"alert-danger"}" role="alert" style="margin-top: 50px;">
                ${mess}
           </div>
        </c:if>
        <div class="w-50 mx-auto mt-5">
            <form action="productManage" method="POST">
                <input name="action" value="insert" hidden/>
                <div class="row g-3">
                    <div class="col-6">
                        <label class="fw-bold fs-5">Image: </label>
                        <input name="image" class="w-100 p-2" value="${image}" type="file"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Code: </label>
                        <input name="product_id" class="w-100 p-2" value="${product_id}" required/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Name: </label>
                        <input name="name" value="${name}" class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Quantity </label>
                        <input name="quantity"
                               value="${quantity}" 
                               type="number" min="1" max="1000" required class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Price: </label>
                        <input name="price"
                               value="${price}" 
                               type="number" required class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Categories: </label>
                        <c:set var="cid" value="${cid}"/>
                        <select class="w-100 p-2" name="category" require>
                            <c:forEach var="cate" items="${listCate}">
                                <option 
                                    ${cate.getCategory_id()==cid?'selected':''}
                                    value="${cate.getCategory_id()}">${cate.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12">
                        <label class="fw-bold fs-5">Describe: </label>
                        <textarea id="editor1" name="describe" 
                               value="${describe}"  class="w-100 p-2"/>
                            ${describe}
                        </textarea>
                    </div>
                    
                </div>
                <button type="submit" class="px-3 py-2 text-white bg-success border-0 rounded-lg mt-3">Submit</button>
                <button type="reset" class="px-3 py-2 text-white bg-danger border-0 rounded-lg mt-3">Reset</button>
            </form>
        </div>
<script src="ckeditor/ckeditor.js"></script>
                        <script>
        CKEDITOR.replace('editor1');
</script>
    </body>
</html>

