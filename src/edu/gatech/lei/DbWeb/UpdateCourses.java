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
 * Servlet implementation class UpdateCourse
 */
@WebServlet("/UpdateCourses")
public class UpdateCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String ucourseid = request.getParameter("ucourseid");
		String uroomsize = request.getParameter("uroomsize");
		String uspring = request.getParameter("uspring");
		String usummer = request.getParameter("usummer");
		String ufall = request.getParameter("ufall");	
		
		String databaseURL = "jdbc:mysql://localhost:3306/firstdb";
        String user = "root";
        String password = "root";
        Connection conn = null;
        
        PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>Student: Course Catalog</title></head>");
		out.print("<style>body{width: 800px; margin: 20px auto; text-align: center;}</style>");
		out.print("<body><h2>Student: Course Catalog</h2><hr>");
		out.println("<table border=1 width=100% style=\"font-family:sans-serif; font-size:10pt;\">");
        out.println("<tr><th>COURSE ID</th><th>COURSE NAME</th><th>SPRING</th><th>SUMMER</th><th>FALL</th><th>CLASSROOM SIZE</th><tr>");
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }      
            PreparedStatement ips = conn.prepareStatement("UPDATE coursetb SET SPRING=?, SUMMER=?, FALL=?, CLASSROOM_SIZE=?  WHERE COURSE_ID=?");
            ips.setString(1, uspring);
            ips.setString(2, usummer);
            ips.setString(3, ufall);
            ips.setString(4, uroomsize);
            ips.setString(5, ucourseid);
            ips.executeUpdate();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM coursetb");
			ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	String courseid = rs.getString("COURSE_ID");
            	String coursename = rs.getString("COURSE_NAME");
            	String spring = rs.getString("SPRING");
            	String summer = rs.getString("SUMMER");
            	String fall = rs.getString("FALL");
            	String roomsize = rs.getString("CLASSROOM_SIZE");
            	out.println("<tr><td>" + courseid + "</td><td>" + coursename + "</td><td>" + spring + "</td><td>" 
            	              + summer + "</td><td>" + fall + "</td><td>" + roomsize + "</td></tr>");
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
