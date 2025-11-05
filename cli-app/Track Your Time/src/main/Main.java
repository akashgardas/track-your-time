package main;

import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDao;

public class Main {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			UserDao users = new UserDao();
			
			users.selectUsers();
			
			System.out.println("UserName: ");
			String userName = sc.nextLine();
			System.out.println("Email: ");
			String userEmail = sc.nextLine();
			System.out.println("Password: ");
			String password = sc.nextLine();
			
			users.addUser(userName, userEmail, password);
			
			users.selectUsers();
			
			users.closeSafe();
			sc.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
