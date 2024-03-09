<%-- 
    Document   : viewgrade
    Created on : Mar 9, 2024, 11:34:31 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Grade</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Department</th>
                    <th>Term</th>
                    <th>Grade Category</th>
                    <th>Grade Items</th>
                    <th>Weight</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach items="${requestScope.departments}" var="d">
                    <tr>
                        <td>${d.depname}</td>
                    </tr>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
    </body>
</html>
