<%-- 
    Document   : syllabus
    Created on : Mar 19, 2024, 8:48:32 PM
    Author     : kieuthanhtheanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Syllabus</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
                color: buttontext;
            }

            th {
                background-color: #f2f2f2;
            }
            h2{
                color: aqua;
            }
            body {
                background-color: orange;
            }
            .subcode {
                text-align: center;
                color: blue;
                font-weight: bold;
            }
            .error {
                color: blue;
            }
        </style>
    </head>
    <body>
        <button class="home" onclick="goToHomePage()">Home</button>
        <h2>Syllabus</h2>
        <form action="syllabus" method="post">
            Enter Subject Code: <input type="text" name="subcode" />
            <input type="submit" value="View"/>
        </form>
        <c:if test="${not empty requestScope.subdetails}">
            <div class="subcode">${sessionScope.subcode}</div>
        </c:if>
        <c:if test="${empty requestScope.subdetails}">
            <p class="error">Enter right subject code again</p>
        </c:if>
        <c:if test="${not empty requestScope.subdetails}">
            <table>
                <tr>
                    <th>Category</th>
                    <th>Type</th>
                    <th>Weight</th>
                    <th>Duration Time</th>
                    <th>Type Question</th>
                    <th>Number Of Question</th>
                </tr>
                <c:forEach items="${requestScope.subdetails}" var="s">
                    <tr>
                        <td>${s.category}</td>
                        <td>${s.type}</td>
                        <td>${s.weight}%</td>
                        <td>${s.duration}</td>
                        <td>${s.typeQuestion}</td>
                        <td>${s.noQuestion}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
    <script>
        function goToHomePage() {
            window.location.href = "homestudent";
        }
    </script>
</html>
