<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
    <div class="page-container">
        <form action="register" method="post">
            <h2>Create Account</h2>
            <div class="form-group">
                <label for="uname">User Name:</label>
                <input type="text" id="uname" name="uname" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="pass">Password:</label>
                <input type="password" id="pass" name="password" required>
            </div>
            <div>
                <button type="submit" class="btn btn-secondary">Create Account</button>
            </div>
            <p class="form-links">Already have an account? <a href="login">Login</a></p>
        </form>
    </div>
</body>
</html>