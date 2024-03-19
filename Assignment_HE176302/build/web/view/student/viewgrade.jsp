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
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            .red {
                color: red;
                font-weight: bold;
            }
            .green {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body>

        <button class="home" onclick="goToHomePage()">Home</button>

        <form id="sendata" action="grade" method="POST">
            <input type="hidden" id="semId" name="semid" value="">
            <input type="hidden" id="subId" name="subid" value="">
            <table>
                <thead>
                    <tr>
                        <th>Term</th>
                        <th>Course</th>
                        <th>Grade Items</th>
                        <th>Weight</th>
                        <th>Value</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${sessionScope.semesters}" var="s">
                                <tr>
                                    <td onclick="submitForm('${s.semid}', '')">${s.semName}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>  
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.subjects}" var="sub">
                                <tr>
                                    <td onclick="submitForm('${sessionScope.semid}', '${sub.subid}')">${sub.subname}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.grades}" var="g">
                                <tr>
                                    <td>${g.item}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.grades}" var="g">
                                <tr>
                                    <td>${g.weight}%</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.grades}" var="g">
                                <tr>
                                    <td>${g.value}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <tr>
                    <td>Total:</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${requestScope.sumGrade}</td>
                    <td>
                        <c:if test="${requestScope.sumGrade < 5}">
                            <span class="red">Not Pass</span>
                        </c:if>
                        <c:if test="${requestScope.sumGrade >= 5}">
                            <span class="green">Passed</span>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </body>
    <script>
        function submitForm(semid, subid) {
            var url = "grade?semid=" + semid;
            if (subid != '') {
                url += "&subid=" + subid;
            }
            document.getElementById("sendata").action = url;
            document.getElementById("sendata").submit();
        }

        function goToHomePage() {
            window.location.href = "homestudent";
        }
    </script>
</html>
