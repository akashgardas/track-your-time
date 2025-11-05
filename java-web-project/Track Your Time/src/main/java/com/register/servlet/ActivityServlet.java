package com.register.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.database.dao.ActivitiesDAO;
import com.example.model.Activity;

/**
 * Servlet implementation class ActivityServlet
 */
@WebServlet("/ActivityServlet")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get the session (false - don't create if doesn't exists.
		HttpSession session = request.getSession(false);
		
		// 2. Check if user is logged in
		if (session == null || session.getAttribute("user_id") == null) {
			// redirect to login page if not logged in.
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return; // stop executing
		}
		
		// 3. Get the user id from the session
		int userId = (Integer) session.getAttribute("user_id");
		
		// 4. Get user activities
		ActivitiesDAO activitiesDao = new ActivitiesDAO();
		List<Activity> activityList = activitiesDao.getActivitiesByUserId(userId);
		
		// 5. Forward to the JSP 
		request.setAttribute("activityList", activityList);
		request.getRequestDispatcher("/WEB-INF/views/activities.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
