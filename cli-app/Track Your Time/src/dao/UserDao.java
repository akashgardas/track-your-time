package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao extends Dao {
	public UserDao() throws SQLException {
		super();
	}

	private Statement stmt;
	private PreparedStatement pstmt;
	
	public void selectUsers() throws SQLException {
		stmt = getStatement();
		
		String query = "SELECT * FROM users";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
		
		rs.close();
		stmt.close();
	}
	
	public void addUser(String userName, String userEmail, String password) throws SQLException {
		if(userEmail == "")
			userEmail = null;
		
		pstmt = getPreparedStatement("INSERT INTO users (user_name, user_email, password) VALUES (?, ?, ?)");
		pstmt.setString(1, userName);
		pstmt.setString(2, userEmail);
		pstmt.setString(3, password);
		pstmt.executeUpdate();
		
		pstmt.close();
	}
	
	public void closeSafe() throws SQLException {
		conn.close();
	}
}
