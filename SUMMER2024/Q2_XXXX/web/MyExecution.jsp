<%-- 
    Document   : MyExecution.jsp
    Created on : 1 Sep, 2024, 3:07:04 PM
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
       <form action="show">
          <table>
            <tbody>
                <tr>
                    <td>
                        Enter an integer array:
                    </td>
                    <td>
                        <input type="text" name="arr" value="${arr}">
                    </td>
                </tr>
                <tr>
                    <td>
                       Choose an option:
                    </td>
                    <td>
                        <input type="radio" name="type" value="odd" 
                               ${type.equals("odd") || type == null?"checked":""}> Odd
                        <input type="radio" name="type" value="even"
                               ${type.equals("even")?"checked":""}
                               > Even
                    </td>
                </tr>
                <tr>
                    <td>
                        Result:
                    </td>
                    <td>
                        <input type="text" name="result" value="${count}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="COUNT" />
                    </td>
                     <td>
                         <p style="font-weight: bold; font-style: italic;">${mess}</p>
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
        <h3>List execution: </h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Array</th>
                    <th>Option</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="exe" items="${sessionScope.list}">
                <tr>
                    <td>${exe.getArray()}</td>
                    <td>${exe.getOption()}</td>
                    <td>${exe.getResult()}</td>
                </tr>
             </c:forEach>
            </tbody>
        </table>

    </body>
</html>
