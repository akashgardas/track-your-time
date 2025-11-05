<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
    body { font-family: Arial, sans-serif; display: grid; place-items: center; min-height: 90vh; background-color: #f4f4f4; }
    form { background: #fff; border: 1px solid #ccc; padding: 25px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    div { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; }
    input[type="text"], input[type="email"], input[type="password"] {
        width: 300px; padding: 8px; border: 1px solid #ddd; border-radius: 4px;
    }
    input[type="submit"] {
        width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;
    }
    input[type="submit"]:hover { background-color: #0056b3; }
    p {font-size: small;}
</style>
</head>
<body>
	<form action="RegisterServlet" method="post">
        <h2>Create Account</h2>
        <div>
            <label for="uname">User Name:</label>
            <input type="text" id="uname" name="uname" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="pass">Password:</label>
            <input type="password" id="pass" name="password" required>
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
        <p>Already an existing user? <a href="login.jsp">login</a></p>
    </form>
</body>
</html>