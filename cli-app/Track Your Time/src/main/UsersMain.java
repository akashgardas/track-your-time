package main;

import java.util.Scanner;

import dao.UsersDAO;

public class UsersMain {
	public static void main(String[] args) {
		UsersDAO userDao = new UsersDAO();
		Scanner sc = new Scanner(System.in);
		Boolean runApp = true;
		
		String userName;
		String email;
		String password;
		
		while(runApp) {
			System.out.println("Select Operation");
			System.out.println("\t1. Create User");
			System.out.println("\t2. Read Users");
			System.out.println("\t3. Update User");
			System.out.println("\t4. Delete User");
			System.out.println("\t5. EXIT");
			
			System.out.println("Choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("User Name: ");
				userName = sc.nextLine();
				
				System.out.println("Email: ");
				email = sc.nextLine();
				
				System.out.println("Password: ");
				password = sc.nextLine();
				
				userDao.createUser(userName, email, password);
				break;
				
			case 2:
				System.out.println("==== USERS ====");
				userDao.readUsers();
				System.out.println("===============");
				break;
				
			case 3:
				System.out.println("User Name: ");
				userName = sc.nextLine();
				
				System.out.println("Password: ");
				password = sc.nextLine();
				
				userDao.updateUser(userName, password);
				break;
			
			case 4:
				System.out.println("User Name: ");
				userName = sc.nextLine();
				
				System.out.println("Password: ");
				password = sc.nextLine();
				
				userDao.deleteUser(userName, password);
				break;
				
			default:
				runApp = false;
			}
		}
		
		userDao.closeSafe();
		System.out.println("Application Closed.");
		sc.close();
	}
}
