package edu.gatech.lei.DbWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
	//	boolean success = coordinator.login(username, password);
		
		if (username.equals("Administrator") && password.equals("adminPassword")) {
			RequestDispatcher rd = request.getRequestDispatcher("/adminStudent.jsp");
			rd.forward(request, response);
		} else if(true){
			RequestDispatcher rd = request.getRequestDispatcher("/studentCourse.jsp");
			rd.include(request, response);
		} else{
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		}
	}
}
