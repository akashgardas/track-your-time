<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.model.Activity, java.time.LocalDate, java.time.LocalTime, java.sql.Time" %>
<%
	String today = LocalDate.now().toString();
	Time time = Time.valueOf(LocalTime.now());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Activity</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body style="display:block; min-height: 0; padding: 20px;">

    <header class="header">
        <h1>🚀 Track Your Time</h1>
        <a href="logout">Logout</a> </header>
    
    <div class="activities-layout">
        <div class="activity-manager">
            <h2>Activity Manager</h2>

            <form action="AddActivityServlet" method="POST" class="add-form">
                <div class="form-group">
                    <label for="name">Activity Name</label>
                    <input type="text" id="name" name="activity_name" required>
                </div>
                <div class="form-group">
                    <label for="date">Date</label>
                    <input type="date" id="date" name="activity_date" value="<%= today %>" required>
                </div>
                <div class="form-group">
                    <label for="start">Start Time</label>
                    <input type="time" id="start" name="start_time" required>
                </div>
                <div class="form-group">
                    <label for="end">End Time</label>
                    <input type="time" id="end" name="end_time" value="<%= time %>" required>
                </div>
                
                <button type="submit" class="btn btn-secondary">Add Activity</button>
            </form>
        </div>

        <div class="recent-activities">
            <h2>Recent Activity</h2>

            <div class="table-container">
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
                        <%
                            List<Activity> activityList = (List<Activity>) request.getAttribute("activityList");
                            if (activityList == null || activityList.isEmpty()) {
                        %>
                                <tr>
                                    <td colspan="4" class="empty-message">No user activity found.</td>
                                </tr>
                        <%
                            } else {
                                for (Activity activity : activityList) {
                        %>
                                    <tr>
                                        <td><%= activity.getActivity() %></td>
                                        <td><%= activity.getStartTime() %></td>
                                        <td><%= activity.getEndTime() %></td>
                                        <td><%= activity.getDate() %></td>
                                    </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>	
    </div>
</body>
</html>