package dao;

import java.sql.*;

abstract class Dao {
	private String userName = "root";
	private String password = "akashbuv";
	private String dbURL = "jdbc:mysql://localhost:3306/project_db_track_your_time";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	protected Connection conn;
	
	protected Dao() throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conn = DriverManager.getConnection(dbURL, userName, password);
	}
	
	protected Statement getStatement() throws SQLException {
		return conn.createStatement();
	}
	
	protected PreparedStatement getPreparedStatement(String prepStmt) throws SQLException {
		return conn.prepareStatement(prepStmt);
	}
}
