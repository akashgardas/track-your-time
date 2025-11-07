package com.register.servlet; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Get the current session (if one exists)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 2. Invalidate the session
            // This destroys all session data, including the "user_id"
            session.invalidate();
        }
        
        // 3. Redirect the user back to the public home page
        // Use request.getContextPath() to build a reliable URL
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Just in case, make POST requests do the same thing
        doGet(request, response);
    }
}