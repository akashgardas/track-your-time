package com.register.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import com.database.dao.ActivitiesDAO;

/**
 * Servlet implementation class AddActivityServlet
 */
@WebServlet("/AddActivityServlet")
public class AddActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddActivityServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get user id from the session
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("user_id");
		
		// 2. Security check: ensure user is logged in
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // 3. Add activity to the user
        String activity = request.getParameter("activity_name");
        System.out.println(request.getParameter("start_time")+":00");
        Time startTime = Time.valueOf((String) request.getParameter("start_time") + ":00");
        System.out.println(request.getParameter("end_time"));
        Time endTime = Time.valueOf((String) request.getParameter("end_time"));
        System.out.println(request.getParameter("activity_date"));
        Date date = Date.valueOf((String) request.getParameter("activity_date"));
        
        ActivitiesDAO activitiesDao = new ActivitiesDAO();
        activitiesDao.createActivity(userId, activity, startTime, endTime, date);
        
        // 5. Redirect back to the activities page (Post-Redirect-Get pattern)
        // This will cause ActivityServlet to run again,
        // fetching the NEW list (including the activity you just added).
        response.sendRedirect("ActivityServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
