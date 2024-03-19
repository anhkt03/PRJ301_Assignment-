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

        <form id="semForm" action="grade" method="GET">
            <input type="hidden" id="semIdInput" name="semid" value="">
            <input type="hidden" id="subjectInput" name="subid" value="">
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
            <td>
                <div>
                    <table>
                        <c:forEach items="${requestScope.semesters}" var="s">
                            <tr>
                                <td onclick="submitForm('${s.semid}','')">${s.semName}</td>
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
                                <td onclick="submitForm('${s.semid}','${sub.subid}')">${sub.subname}</td>
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
        </tbody>

    </table>
</body>

<script>
    function submitForm(semid, subid) {
        
//        var url = 'grade?';
//        
//        if(subid==='') {
//            url += 'semid=' + semid;
//        }else {
//            url += 'semid=' + subid;
//        }
        

        
       // document.getElementById("subjectInput").value = subid;
        document.getElementById("semIdInput").value = semid;
            document.getElementById("subjectInput").value = subid;
            document.getElementById("semForm").submit();
//        if(subid == null) {
//            document.getElementById("semIdInput").value = semid;
//            document.getElementById("semForm").submit();
//        }else {
//            //document.getElementById("semForm").submit();
//            document.getElementById("semIdInput").value = semid;
//            document.getElementById("subjectInput").value = subid;
//            document.getElementById("semForm").submit();
//        }
        
    }
</script>
</html>
