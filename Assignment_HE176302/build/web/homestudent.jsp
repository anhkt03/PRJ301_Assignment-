<%-- 
    Document   : homestudent
    Created on : Mar 8, 2024, 12:42:53 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Student</title>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <form action="homestudent" method="POST">
            <div>
                <button type="submit" name="action" value="viewSchedule">View Schedule</button>
            </div>
            <div>
                <button type="submit" name="action" value="viewGrade">View Grade</button>
            </div>
        </form>
    </body>
</html>
