package com.register.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.database.dao.UsersDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get the form data
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// 2. Get UsersDAO connection
		UsersDAO usersDao = new UsersDAO();
		
		// 3. Create new user
		RequestDispatcher dispatcher = null;
		if (usersDao.createUser(username, email, password)) {
			request.setAttribute("status", "Registration Successful");
			dispatcher = request.getRequestDispatcher("success.jsp");
		} else {
			request.setAttribute("status", "Registration Failed!");
			request.setAttribute("msg", "User name or Email already exists!");
			dispatcher = request.getRequestDispatcher("failure.jsp");
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
