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

        <form id="semForm" action="" method="GET">
            <input type="hidden" id="semIdInput" name="semid" value="">
            <!--            <input type="hidden" id="semesterInput" name="semester" value="">-->
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>Term</th>
                    <th>Course</th>
                    <th>Grade Items</th>
                    <th>Weight</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.semesters}" var="s">
                    <tr>
                        <td onclick="submitForm('${s.semid}')">${s.semName}</td>
                    </tr>
                    <c:forEach items="${requestScope.subjects}" var="sub">
                        <tr>
                            <td>a</td>
                        </tr>
                </c:forEach>
            </c:forEach>  
        </tbody>

    </table>
</body>

<script>
    function submitForm(semid) {
        document.getElementById("semIdInput").value = semid;
        document.getElementById("semForm").submit();
    }
</script>
</html>
