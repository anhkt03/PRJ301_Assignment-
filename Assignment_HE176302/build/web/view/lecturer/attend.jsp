<%-- 
    Document   : attend
    Created on : Mar 5, 2024, 11:20:44 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Take Attend</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .timetable {
            padding: 10px;
            margin-bottom: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <button class="timetable" onclick="goToTimeTable()">Time Table</button>

    <form action="attend" method="POST">
        <input type="hidden" name="seid" value="${param.seid}"/>

        <table>
            <thead>
                <tr>
                    <th>MSSV</th>
                    <th>Name</th>
                    <th>Status Attend</th>
                    <th>Comment</th>
                    <th>Time Record</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.attends}" var="a">
                    <tr>
                        <td>${a.student.MSSV}</td>
                        <td>${a.student.sname}</td>
                        <td>
                            ${a.isAttend}
                            <input type="radio" 
                                   ${a.isAttend!=1 ? "checked" : ""}
                                   name="present${a.student.sid}" value="no"> Absent
                            <input type="radio" 
                                   ${a.isAttend == 1 ? "checked" : ""}
                                   name="present${a.student.sid}" value="yes"> Attend
                        </td>
                        <td><input name="comment${a.student.sid}" type="text" value="${a.comment}"></td>
                        <td>${a.recordtime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="Save"/>
    </form>

    <script>
        function goToTimeTable() {
            window.location.href = "timetable";
        }
    </script>
</body>
</html>

