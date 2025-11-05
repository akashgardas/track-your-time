<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Activity" %>

<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.sql.Time" %>

<%
	String today = LocalDate.now().toString();
	Time time = Time.valueOf(LocalTime.now());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Activity</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        .activity-table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin: auto;
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
        }
        
        /* Add Form */
        .add-form {
            background: #f4f4f4;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 8px;
            gap: 10px;
        }
        .add-form div {
            display: flex;
            flex-direction: column;
            flex-grow: 1;
            margin: 20px 0px;
        }
        .add-form label {
            font-weight: bold;
            font-size: 0.9em;
            margin-bottom: 5px;
        }
        .add-form input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .add-form button {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        
        body {
        	display: grid;
        	grid-template-columns: repeat(auto, 4);
        	grid-template-areas:
        	'recent recent recent manager';
        }
        
        .activity-manager {
        	grid-area: manager;
        }
        
        .recent-activities {
        	grid-area: recent;
        }
    </style>
</head>
<body>

<div class="activity-manager">
	<h1>User Activity Manager</h1>

    <form action="AddActivityServlet" method="POST" class="add-form">
        <div>
            <label for="name">Activity Name</label>
            <input type="text" id="name" name="activity_name" required>
        </div>
        <div>
            <label for="date">Date</label>
            <input type="date" id="date" name="activity_date" value="<%= today %>" required>
        </div>
        <div>
            <label for="start">Start Time</label>
            <input type="time" id="start" name="start_time" required>
        </div>
        <div>
            <label for="end">End Time</label>
            <input type="time" id="end" name="end_time" value="<%= time %>" required>
        </div>
        
        <button type="submit">Add Activity</button>
    </form>
</div>

<div class="recent-activities">
    <h1>Recent User Activity</h1>

    <table class="activity-table">
        <thead>
            <tr>
                <th>Activity</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <%-- 
              2. This is a JSP Scriptlet tag. It's for writing Java code.
                 We get the "activityList" from the request (set by the Servlet)
                 and cast it to the correct type.
            --%>
            <%
                List<Activity> activityList = (List<Activity>) request.getAttribute("activityList");

                // 3. Check if the list is null or empty
                if (activityList == null || activityList.isEmpty()) {
            %>
                    <tr>
                        <td colspan="3" class="empty-message">No user activity found.</td>
                    </tr>
            <%
                } else {
                    // 4. Loop through the list using a standard Java for-each loop
                    for (Activity activity : activityList) {
            %>
                        <tr>
                            <td>
                                <%= activity.getActivity() %>
                            </td>
                            <td>
                                <%= activity.getStartTime() %>
                            </td>
                            <td>
                                <%= activity.getEndTime() %>
                            </td>
                            <td>
                            	<%= activity.getDate() %>
                            </td>
                        </tr>
            <%
                    // 7. Re-open the scriptlet to close the loop and the else block
                    }
                }
            %>
        </tbody>
    </table>
</div>	
</body>
</html>