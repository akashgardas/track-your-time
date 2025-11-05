package test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ActivitiesMain {

	public static void main(String[] args) {
		ActivitiesDAO activitiesDao = new ActivitiesDAO();
		Scanner sc = new Scanner(System.in);
		
		int userId;
		String activityName;
		String startTime;
		String endTime;
		String date;
		
		Boolean runApp = true;
		
		while(runApp) {
			System.out.println("Select Operation");
			System.out.println("\t1. Create Acitivity");
			System.out.println("\t2. Read Acitivities");
			System.out.println("\t3. Update Activity");
			System.out.println("\t4. Delete Activity");
			System.out.println("\t5. EXIT");
			
			System.out.println("Choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("User Name: ");
				userId = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Activity: ");
				activityName = sc.nextLine();
				
				System.out.println("Start Time");
				startTime = sc.nextLine();
				
				activitiesDao.createActivity(userId, activityName, Time.valueOf(startTime), Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()));
				break;
			
			case 2:
				System.out.println("===== Activities =====");
				activitiesDao.readActivities();
				System.out.println("======================");
				break;
				
			default:
				runApp = false;
			}
		}
		
		sc.close();
		activitiesDao.closeSafe();
		System.out.println("Application Closed!");
	}
}
