<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
    <div class="page-container">
        <h2 style="color: #dc3545;"><%= request.getAttribute("status") %></h2>
        <p><%= request.getAttribute("msg") %></p>
        <p class="form-links"><a href="login">Back to Login</a></p>
    </div>
</body>
</html>