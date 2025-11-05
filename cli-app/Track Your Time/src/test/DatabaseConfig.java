package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class DatabaseConfig {
	// Database details
	final private String userName = "Your User Name";
	final private String password = "Your Password";
	
	// JDBC details
	final private String driver = "com.mysql.cj.jdbc.Driver";
	final private String url = "jdbc:mysql://localhost:3306/Your Database Name";
	
	// Database Connection
	protected Connection con;
	
	public DatabaseConfig() {
		try {
			con = getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("EXCEPTION IN UserDAO.java");
			System.out.println(e.getMessage());
		}
	}
	
	private Connection  getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, userName, password);
		
		return con;
	}
}
