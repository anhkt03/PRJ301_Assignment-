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
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
        }

        h1 {
            color: #333;
        }

        form {
            margin-top: 30px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 10px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
    <body>
        <h1>FPT University Academic Portal</h1>
        <h2>Home Student</h2>
        <form action="homestudent" method="POST">
            <div>
                <button type="submit" name="action" value="viewSchedule">View Schedule</button>
            </div>
            <div>
                <button type="submit" name="action" value="viewGrade">View Grade</button>
            </div>
        </form>
        <button class="logout" onclick="logout()">Logout</button>
    </body>
    <script>
        function logout() {
            window.location.href = "login";
        }
    </script>
</html>
