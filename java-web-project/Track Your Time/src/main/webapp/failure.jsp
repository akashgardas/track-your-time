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
        <h2><%= request.getAttribute("status") %></h2>
        <p><%= request.getAttribute("msg") %></p>
        <p style="font-size:small;">Don't have an account? <a href="register.jsp">register</a></p>
        <p style="font-size:small;">Already an existing user? <a href="login.jsp">login</a></p>
    </div>
</body>
</html>