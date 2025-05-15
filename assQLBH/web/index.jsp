<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/index.css"/>
    </head>
    <body>
        <div id="panner">
            <jsp:include page="panner.jsp"/>
        </div>
        <div class="container">
            <div class="row">
                <div class="menu_category col-lg-2">
                    <jsp:include page="menu.jsp"/>
                </div>
                <div class="content_category col-lg-10">
                    <jsp:include page="content.jsp"/>
                </div>
            </div>
        </div>
    </body>
</html>
