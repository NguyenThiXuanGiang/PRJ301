<%-- 
    Document   : home
    Created on : 2 Sep, 2024, 1:41:44 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Execution ID</th>
                    <th>Array</th>
                    <th>Result</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <!--//hien thi du lieu-->
            <c:forEach var="exe" items="${list}">
                 <tr>
                     <td>
                        <a href="?service=detail&id=${exe.getExecutionID()}">${exe.getExecutionID()}</a>
                     </td>
                     <td>
                         <c:forEach var="item" items="${exe.getList()}">
                             <span>${item.getArrayItem()} </span>
                         </c:forEach>
                     </td>
                     <td>
                         ${exe.getResult()}
                     </td>
                     <td>
                         <a href="?service=delete&id=${exe.getExecutionID()}">Delete</a>
                     </td>
                </tr>
            </c:forEach>
               
            </tbody>
        </table>

        <c:set var="detail" value="${item}"/>
        <table>
            <tbody>
                <tr>
                    <td>Execution ID</td>
                    <td>
                        <input type="text" name="executionID" value="${detail.getExecutionID()}">
                    </td>
                </tr>
                <tr>
                    <td>Array</td>
                     <c:set var="arr" value=""/>
                     <c:forEach var="item" items="${detail.getList()}">
                         <c:set var="arr" value="${arr}${item.getArrayItem()} "/>
                     </c:forEach>
                    <td>
                        <input type="text" name="array" value="${arr}">
                    </td>
                </tr>
                <tr>
                    <td>Result</td>
                    <td>
                        <input type="text" name="result"  value="${detail.getResult()}">
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
