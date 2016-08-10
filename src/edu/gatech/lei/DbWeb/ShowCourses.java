package edu.gatech.lei.DbWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowCourses
 */
@WebServlet("/ShowCourses")
public class ShowCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String databaseURL = "jdbc:mysql://localhost:3306/ARMS";
        String user = "arms";
        String password = "arms";
        Connection conn = null;
        
        PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>Course Catalog</title></head>");
		out.print("<style>body{width: 800px; margin: 20px auto; text-align: center;}</style>");
		out.print("<body><h2>Course Catalog</h2><hr>");
		out.println("<table border=1 width=100% style=\"font-family:sans-serif; font-size:10pt;\">");
        out.println("<tr><th>CourseId</th><th>CourseName</th><th>MaxSize</th><th>CourseAvailability</th><th>CoursePrerequisites</th><tr>");
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);
                       
            PreparedStatement ps = conn.prepareStatement(
            		"SELECT t1.CourseId, t1.CourseName, t1.MaxSize, t2.CourseAvailability, t2.CoursePrerequisites FROM Courses AS t1 INNER JOIN CourseConstraints AS t2 ON t1.CourseId = t2.CourseId");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	String courseid = rs.getString("CourseId");
            	String coursename = rs.getString("CourseName");
            	String maxsize = rs.getString("MaxSize");
            	String availability = rs.getString("CourseAvailability");
            	String prerequisites = rs.getString("CoursePrerequisites");
            	
            	out.println("<tr><td align=\"center\">" + courseid + "</td><td>" + coursename + "</td><td align=\"center\">" 
            	+ maxsize + "</td><td align=\"center\">" + availability + "</td><td align=\"center\">" + prerequisites + "</td></tr>");
            }
            out.println("</table></html></body>");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
	}

}
