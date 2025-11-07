<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Track Your Time</title>
    <link rel="stylesheet" href="style.css">
    <style>
    	/* --- Global Styles --- */
		body {
		    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
		    background-color: #f0f2f5;
		    margin: 0;
		    padding: 20px;
		    color: #333;
		    display: grid;
		    place-items: center;
		    min-height: 90vh;
		}
		
		/* --- Centered Page Container (for Login, Register, Index) --- */
		.page-container {
		    background: #ffffff;
		    padding: 2.5em;
		    border-radius: 8px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
		    max-width: 450px;
		    width: 90%;
		    margin: 2em auto;
		    text-align: center;
		}
		
		.page-container h1, .page-container h2 {
		    margin-top: 0;
		    color: #111;
		}
		
		.page-container p {
		    font-size: 1.1em;
		    color: #555;
		}
		
		/* --- Form Styles --- */
		.form-group {
		    margin-bottom: 1.5em;
		    text-align: left;
		}
		
		.form-group label {
		    display: block;
		    margin-bottom: 8px;
		    font-weight: 600;
		    font-size: 0.9em;
		}
		
		.form-group input[type="text"],
		.form-group input[type="email"],
		.form-group input[type="password"],
		.form-group input[type="date"],
		.form-group input[type="time"] {
		    width: 100%;
		    padding: 12px;
		    border: 1px solid #ddd;
		    border-radius: 6px;
		    box-sizing: border-box; /* Important for 100% width */
		}
		
		.form-links {
		    font-size: 0.9em;
		    margin-top: 20px;
		}
		
		.form-links a {
		    color: #007bff;
		    text-decoration: none;
		}
		
		/* --- Button Styles --- */
		.btn {
		    text-decoration: none;
		    padding: 12px 25px;
		    margin: 0 10px;
		    border-radius: 6px;
		    font-size: 16px;
		    font-weight: 600;
		    border: none;
		    cursor: pointer;
		    display: inline-block;
		    transition: background-color 0.2s ease;
		    width: 100%;
		    box-sizing: border-box;
		}
		
		.btn-primary {
		    background-color: #007bff;
		    color: white;
		}
		.btn-primary:hover {
		    background-color: #0056b3;
		}
		
		.btn-secondary {
		    background-color: #28a745;
		    color: white;
		}
		.btn-secondary:hover {
		    background-color: #218838;
		}
		
		.btn-container {
		    margin-top: 30px;
		    display: flex;
		    gap: 15px;
		}
		.btn-container .btn {
		    margin: 0;
		}
		
		
		/* --- Activities Page Layout --- */
		.header {
		    width: 100%;
		    max-width: 1400px;
		    background: #fff;
		    padding: 15px 30px;
		    border-radius: 8px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    box-sizing: border-box;
		    margin-bottom: 20px;
		}
		.header h1 {
		    margin: 0;
		    font-size: 1.5em;
		}
		.header a {
		    color: #dc3545;
		    text-decoration: none;
		    font-weight: 600;
		}
		
		.activities-layout {
		    display: grid;
		    grid-template-columns: 1fr 380px;
		    grid-template-areas: 'recent manager';
		    gap: 20px;
		    width: 100%;
		    max-width: 1400px;
		    margin: 0 auto;
		}
		
		.recent-activities { 
		    grid-area: recent;
		    background: #fff;
		    padding: 30px;
		    border-radius: 8px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
		}
		.activity-manager { 
		    grid-area: manager;
		    background: #fff;
		    padding: 30px;
		    border-radius: 8px;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
		}
		
		/* --- Activity Table --- */
		.table-container {
		    overflow-x: auto; /* Makes table scrollable on small screens */
		}
		
		.activity-table {
		    width: 100%;
		    border-collapse: collapse;
		    margin-top: 20px;
		}
		.activity-table th, .activity-table td {
		    border: 1px solid #ddd;
		    padding: 12px;
		    text-align: left;
		}
		.activity-table th {
		    background-color: #007bff;
		    color: white;
		}
		.activity-table tr:nth-child(even) {
		    background-color: #f9f9f9;
		}
		.activity-table tr:hover {
		    background-color: #f1f1f1;
		}
		
		.empty-message {
		    color: #777;
		    font-style: italic;
		    padding: 20px;
		    text-align: center;
		}
		
		/* --- Add Activity Form --- */
		.add-form {
		    display: flex;
		    flex-direction: column;
		    gap: 15px;
		}
		
		
		/* --- Responsive (Mobile-First) --- */
		@media (max-width: 900px) {
		    body {
		        padding: 10px;
		    }
		    
		    /* Stack the manager and recent activities on tablets/phones */
		    .activities-layout {
		        grid-template-columns: 1fr;
		        grid-template-areas:
		            'manager'
		            'recent';
		    }
		    
		    .header {
		        flex-direction: column;
		        gap: 15px;
		    }
		}
		
		@media (max-width: 480px) {
		    .page-container {
		        width: 95%;
		        padding: 1.5em;
		    }
		    
		    .btn-container {
		        flex-direction: column;
		    }
		}
    </style>
</head>
<body>
    <div class="page-container">
        <h1>🚀 Welcome to Track Your Time</h1>
        <p>Your personal assistant for managing tasks and tracking productivity.</p>
        
        <div class="btn-container">
            <a href="login" class="btn btn-primary">Login</a>
            <a href="register" class="btn btn-secondary">Register</a>
        </div>
    </div>
</body>
</html>