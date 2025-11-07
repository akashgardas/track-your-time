package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource; // Import DataSource

//import javax.naming.Context;     // Import JNDI classes
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

//Import the HikariCP classes
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class UsersDAO {
	
	private DataSource ds; // Holds the connection pool

	// Constructor: Looks up the DataSource when the DAO is created
	public UsersDAO() {
//		try {
//			Context initContext = new InitialContext();
//			Context envContext = (Context) initContext.lookup("java:comp/env");
//			// This is the JNDI name we configured in Tomcat
//			this.ds = (DataSource) envContext.lookup("jdbc/trackYourTimeProjectDB"); 
//		} catch (NamingException e) {
//			// This is a critical failure, the app can't connect to the DB
//			throw new RuntimeException("Could not initialize DataSource", e);
//		}
		
		try {
			// Manually force the Java Driver Manager to load the MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// This will crash the app, it tells us the driver is missing
			throw new RuntimeException("Could not find MySQL JDBC Driver in classpath", e);
		}
		
		// 1. Read the credentials from the environment variables
				String dbHost = System.getenv("DB_HOST");
				String dbPort = System.getenv("DB_PORT");
				String dbName = System.getenv("DB_NAME");
				String dbUser = System.getenv("DB_USER");
				String dbPass = System.getenv("DB_PASSWORD");
				
				// 2. Build the new JDBC URL (with SSL for cloud)
				String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?sslMode=REQUIRED";
				
				// 3. Configure the Hikari Connection Pool
				HikariConfig config = new HikariConfig();
				config.setJdbcUrl(jdbcUrl);
				config.setUsername(dbUser);
				config.setPassword(dbPass);
				
				// Optional: Pool tuning settings
				config.setMaximumPoolSize(10); // Max 10 connections
				config.addDataSourceProperty("cachePrepStmts", "true");
				config.addDataSourceProperty("prepStmtCacheSize", "250");
				config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

				// 4. Create the DataSource
				// This replaces your entire JNDI lookup
				try {
					this.ds = new HikariDataSource(config);
				} catch (Exception e) {
					throw new RuntimeException("Could not initialize HikariDataSource", e);
				}
	}
	
	

	// 1. Create
	public boolean createUser(String userName, String userEmail, String password) {
		String insertStmt = "INSERT INTO users (user_name, user_email, password) VALUES (?, ?, ?)";
		
		// try-with-resources automatically gets a connection and closes it
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(insertStmt)) {
			
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, password);
			
			try {
				pstmt.execute();
				System.out.println("User Created!");
				return true;
			} catch (Exception e) {
				System.out.println("EXCEPTION IN UserDAO.java");
				System.out.println("EXCEPTION IN createUser() / pstmt.execute()");
				System.out.println(e.getMessage());
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN createUser()");
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	// 2.1 Read Users
	public void readUsers() {
		String selectStmt = "SELECT * FROM users";

		// All resources (con, stmt, rs) are closed automatically
		try (Connection con = ds.getConnection();
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(selectStmt)) {
			
			System.out.println("user_id \t user_name \t user_email \t password");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " \t " + rs.getString(2) + " \t " + rs.getString(3) + " \t " + rs.getString(4));
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN readUsers()");
			System.out.println(e.getMessage());
		}
	}
		
	// 2.2 Get a Specific User ID
	public int getUserID(String userName, String password) {
		int userId = 0;
		String selectStmt = "SELECT user_id FROM users WHERE user_name = ? AND password = ?";
		
		try (Connection conn = ds.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(selectStmt)) {
				ps.setString(1, userName);
				ps.setString(2, password);
				
				if (ps.execute()) {
					try (ResultSet rs = ps.getResultSet()) {
						rs.next();
						userId = rs.getInt(1);
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN getUserID()");
			System.out.println(e.getMessage());
		}
		
		return userId;
	}
	
	// 3. Update User
	public void updateUser(String userName, String newPassword) {
		String updateStmt = "UPDATE users SET password = ? WHERE user_name = ?";
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(updateStmt)) {
			
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userName);
			
			// Use executeUpdate() for UPDATE, it returns the number of rows affected
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Updated!");
			} else {
				System.out.println("User not found.");
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN updateUser()");
			System.out.println(e.getMessage());
		}
	}
	
	// 4. Delete User
	public void deleteUser(String userName, String password) {
		String userPassword = null;
		String selectStmt = "SELECT password FROM users WHERE user_name = ?";
		String deleteStmt = "DELETE FROM users WHERE user_name = ?";

		// We use one connection for both operations
		try (Connection con = ds.getConnection()) {
			
			// First, get the password
			try (PreparedStatement selectPstmt = con.prepareStatement(selectStmt)) {
				selectPstmt.setString(1, userName);
				
				try (ResultSet rs = selectPstmt.executeQuery()) {
					if (rs.next()) {
						userPassword = rs.getString(1);
					} else {
						System.out.println("Couldn't find user!");
						return; // Exit method
					}
				}
			}
			
			// Now, check password and delete
			if(!password.equals(userPassword)) {
				System.out.println("INVALID PASSWORD");
			} else {
				// Use the same connection 'con'
				try (PreparedStatement deletePstmt = con.prepareStatement(deleteStmt)) {
					deletePstmt.setString(1, userName);
					deletePstmt.execute();
					System.out.println("Deleted Successfully!");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN deleteUser()");
			System.out.println(e.getMessage());
		}
	}
	
	// Validate User
	public boolean validateUser(String userName, String password) {
		String selectStmt = "SELECT password FROM users WHERE user_name = ?";
		String userPassword = null;
		
		try (Connection conn = ds.getConnection()) {
			// 1. Get the password
			try (PreparedStatement ps = conn.prepareStatement(selectStmt)) {
				ps.setString(1, userName);
				
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						userPassword = rs.getString(1);
					} else {
						System.out.println("Couldn't find user!");
						return false;
					}
				}
			}
			
			// 2. Validate Password
			if(!password.equals(userPassword)) {
				System.out.println("INVALID PASSWORD");
				return false;
			} else {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println("EXCEPTION IN validateUser()");
			System.out.println(e.getMessage());
		}
		return false;
	}
}