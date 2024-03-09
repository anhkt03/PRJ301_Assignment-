<%-- 
    Document   : home
    Created on : Mar 3, 2024, 11:11:57 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Lecturer</title>
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
</head>

<body>
    <h1>FPT University Academic Portal</h1>
    <h2>Home Lecturer</h2>
    <form action="home" method="POST">
        <div>
            <button type="submit" name="action" value="viewTimetable">View Timetable</button>
        </div>
        <div>
            <button type="submit" name="action" value="editgrade">Edit Grade For Student</button>
        </div>
    </form>
</body>

</html>
