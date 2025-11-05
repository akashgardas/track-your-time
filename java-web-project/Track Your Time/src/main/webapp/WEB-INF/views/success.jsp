<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= request.getAttribute("status") %></title>
<style>
    body { font-family: Arial, sans-serif; display: grid; place-items: center; min-height: 90vh; background-color: #f4f4f4; text-align: center; }
    div { background: #fff; border: 1px solid #ccc; padding: 30px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    a { color: #007bff; text-decoration: none; }
</style>
</head>
<body>
    <div>
        <h2>Registration Successful!</h2>
        <p>Your account has been created.</p>
        <p><a href="register">Register Another User</a></p>
        <p><a href="login">login</a></p>
    </div>
</body>
</html>