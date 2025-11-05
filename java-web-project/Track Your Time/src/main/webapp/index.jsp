<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Track Your Time</title>
<style>
    body { font-family: Arial, sans-serif; display: grid; place-items: center; min-height: 90vh; background-color: #f4f4f4; text-align: center; }
    .container { background: #fff; border: 1px solid #ccc; padding: 40px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h1 { margin-top: 0; }
    .nav { margin-top: 30px; }
    .nav a {
        text-decoration: none;
        padding: 12px 25px;
        margin: 0 10px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
    }
    .nav a.login {
        background-color: #007bff;
        color: white;
    }
    .nav a.register {
        background-color: #28a745;
        color: white;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>🚀 Welcome to Track Your Time</h1>
        <p>Your personal assistant for managing tasks and tracking productivity.</p>
        
        <div class="nav">
            <a href="login" class="login">Login</a>
            <a href="register" class="register">Register</a>
        </div>
    </div>
</body>
</html>