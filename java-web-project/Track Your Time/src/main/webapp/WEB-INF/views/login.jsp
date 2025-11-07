<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
    <div class="page-container">
        <form action="login" method="post">
            <h2>Login</h2>
            <div class="form-group">
                <label for="uname">User Name:</label>
                <input type="text" id="uname" name="uname" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="pass">Password:</label>
                <input type="password" id="pass" name="password" required>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <p class="form-links">New user? <a href="register">Register</a></p>
        </form>
    </div>
</body>
</html>