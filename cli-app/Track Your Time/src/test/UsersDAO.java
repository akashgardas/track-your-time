package test;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO extends DatabaseConfig{
	// CRUD Operations
	// 1. Create
	public void createUser(String userName, String userEmail, String password) {
		try {
			// Preparing Statement
			String insertStmt = "INSERT INTO users (user_name, user_email, password) VALUES (?, ?, ?)";
			final PreparedStatement pstmt = con.prepareStatement(insertStmt);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, password);
			
			pstmt.execute();
			System.out.println("User Created!");
			
			// Closing statement
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN createUser()");
			System.out.println(e.getMessage());
		}
	}
	
	// 2. Read Users
	public void readUsers() {
		try {
			String selectStmt = "SELECT * FROM users";
			final Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectStmt);
			
			System.out.println("user_id \t user_name \t user_email \t password");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " \t " + rs.getString(2) + " \t " + rs.getString(3) + " \t " + rs.getString(4));
			}
			
			// Closing Objects
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN readUsers()");
			System.out.println(e.getMessage());
		}
	}
	
	// 3. Update User
	public void updateUser(String userName, String newPassword) {
		String updateStmt = "UPDATE users SET password = ? WHERE user_name = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(updateStmt);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userName);
			
			Boolean result = pstmt.execute();
			if (result) {
				ResultSet rs = pstmt.getResultSet();
				
				System.out.println("user_id \t user_name \t user_email \t password");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " \t " + rs.getString(2) + " \t " + rs.getString(3) + " \t " + rs.getString(4));
				}
				
				// Close result set
				rs.close();
			} else {
				System.out.println("Updated!");
			}
			
			// Close Statement
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN updateUser()");
			System.out.println(e.getMessage());
		}
	}
	
	// 4. Delete User
	public void deleteUser(String userName, String password) {
		PreparedStatement pstmt;
		String userPassword = null;
		try {
			String selectStmt = "SELECT password FROM users WHERE user_name = ?";
			pstmt = con.prepareStatement(selectStmt);
			pstmt.setString(1, userName);
			Boolean result = pstmt.execute();
			
			if (result) {
				ResultSet rs = pstmt.getResultSet();
				rs.next();
				userPassword = rs.getString(1);
				
				// Close result set
				rs.close();
			} else {
				System.out.println("Couldn't find user!");
			}
			pstmt.close();
			
			if(!password.equals(userPassword)) {
				System.out.println(userPassword + " " + password);
				System.out.println("INVALID PASSWORD");
			} else {
				String deleteStmt = "DELETE FROM users WHERE user_name = ?";
				pstmt = con.prepareStatement(deleteStmt);
				pstmt.setString(1, userName);
				pstmt.execute();
				
				System.out.println("Deleted Successfully!");
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN deleteUser()");
			System.out.println(e.getMessage());
		}
	}
	
	// Closing Connection
	public void closeSafe() {
		try {
			con.close();
			System.out.println("Connection Closed -- UserDAO.java");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
