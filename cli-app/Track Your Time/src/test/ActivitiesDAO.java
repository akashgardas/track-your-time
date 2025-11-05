package test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class ActivitiesDAO extends DatabaseConfig {
	// CRUD Operations
	// 1. Create
	public void createActivity(int userId, String activityName, Time startTime, Time endTime, Date date) {
		String insertStmt = "INSERT INTO activities VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(insertStmt);
			pstmt.setInt(1, userId);
			pstmt.setString(2, activityName);
			pstmt.setTime(3, startTime);
			pstmt.setTime(4, endTime);
			pstmt.setDate(5, date);
			
			pstmt.execute();
			System.out.println("Activity Created!");
			
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN createActivity()");
			System.out.println(e.getMessage());
		}
	}
	
	// 2. Read Activities
	public void readActivities() {
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM activities");
			
			System.out.println("User Id \t Activity Name \t Start Time \t End Time \t Date");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " \t " + rs.getString(2) + " \t " + rs.getTime(3) + " \t " + rs.getTime(4) + " \t " + rs.getDate(5));
			}
			
			rs.close();
			
			stmt.close();
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN readActivities()");
			System.out.println(e.getMessage());
		}
	}
	
	// Closing Connection
	public void closeSafe() {
		try {
			con.close();
			System.out.println("Connection Closed -- ActivitesDAO.java");
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN ActivitiesDAO.java");
			System.out.println("EXCEPTION IN closeSafe()");
			System.out.println(e.getMessage());
		}
	}
}
