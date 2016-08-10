package edu.gatech.lei.DbWeb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentCourse
 */
@WebServlet("/StudentCourse")
public class StudentCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		response.setContentType("text/html");
		String courses[] = request.getParameterValues("courses");
		String priority = request.getParameter("priority");
		String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		request.setAttribute("currentTime", currentTime);
		request.setAttribute("selectedCourse", "Test 1");
		request.setAttribute("priority", priority);
		request.getRequestDispatcher("/studentRequest.jsp").forward(request, response);
	}

}
