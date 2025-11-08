package com.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource; // Import DataSource

//import javax.naming.Context;     // Import JNDI classes
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

//Import the HikariCP classes
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.example.model.Activity;

public class ActivitiesDAO {
	
	// Holds the connection pool
	private DataSource ds = ConnectionManager.getDataSource(); 
	
	// CRUD Operations
	// 1. Create
	public void createActivity(int userId, String activityName, Time startTime, Time endTime, Date date) {
		String insertStmt = "INSERT INTO activities (user_id, activity_name, start_time, end_time, date) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(insertStmt)) {
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, activityName);
			pstmt.setTime(3, startTime);
			pstmt.setTime(4, endTime);
			pstmt.setDate(5, date);
			
			pstmt.execute();
			System.out.println("Activity Created!");
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN createActivity()");
			System.out.println(e.getMessage());
		}
	}
	
	// 2.1 Read Activities
	public void readActivities() {
		
		try (Connection con = ds.getConnection();
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM activities")) {
			
			System.out.println("User Id \t Activity Name \t Start Time \t End Time \t Date");
			while(rs.next()) {
				System.out.println(rs.getInt(2) + " \t " + rs.getString(3) + " \t " + rs.getTime(4) + " \t " + rs.getTime(5) + " \t " + rs.getDate(6));
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN readActivities()");
			System.out.println(e.getMessage());
		}
	}
	
	// 2.2 Read specific user activities.
	public List<Activity> getActivitiesByUserId(int userId) {
		List<Activity> activityList = new ArrayList<>();
		String selectQuery = "SELECT activity_name, start_time, end_time, date FROM activities WHERE user_id = ? order by date DESC, end_time DESC, start_time DESC";
		String activity;
		Time startTime, endTime;
		Date date;
		
		try (Connection conn = ds.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
				ps.setInt(1, userId);
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						activity = rs.getString(1);
						startTime = rs.getTime(2);
						endTime = rs.getTime(3);
						date = rs.getDate(4);
						
						activityList.add(new Activity(activity, startTime, endTime, date));
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN getActivitiesByUserId()");
			System.out.println(e.getMessage());
		}
		
		return activityList;
	}
}