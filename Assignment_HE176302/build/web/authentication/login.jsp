<%-- 
    Document   : login
    Created on : Feb 29, 2024, 8:59:41 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FPT University Academic Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .form-control {
            margin-bottom: 15px;
        }
        .form-control input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-control input[type="submit"] {
            background-color: #0056b3;
            color: white;
            cursor: pointer;
        }
        .form-control input[type="submit"]:hover {
            background-color: #004494;
        }
        .message {
            text-align: center;
            margin-top: 20px;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>FPT University Academic Portal</h1>
        <div>
            <form action="login" method="POST" class="form-control">
                <div>
                    Username: <input type="text" name="username"/>
                </div>
                <div>
                    Password: <input type="password" name="password"/>
                </div>
                <div>
                    <input type="submit" value="Login"/>
                </div>
            </form>
            <div class="message">
                <!-- Message to display login success or failure -->
                ${requestScope.data}
            </div>
        </div>
    </div>
</body>
</html>

