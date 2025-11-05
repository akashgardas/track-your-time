package com.register.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.database.dao.UsersDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward to the hidden jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		UsersDAO usersDao = new UsersDAO();
		RequestDispatcher dispatcher = null;
		
		int userId = usersDao.getUserID(username, password);
		
		// 1. Login Successful
		if (userId > 0) {
			// Get the current session or create one
			HttpSession session = request.getSession();
			// Store userid in session
			session.setAttribute("user_id", userId);
			// Send redirect (PRG pattern)
			response.sendRedirect("ActivityServlet");
		}
		// 2. Login Failure
		else {
			request.setAttribute("status", "Login Failed");
			request.setAttribute("msg", "Invalid User name or Password!");
			
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/failure.jsp");
			dispatcher.forward(request, response);
		}
	}

}
