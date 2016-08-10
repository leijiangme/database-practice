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

@WebServlet("/MaxCourses")
public class MaxCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String maxclasssize = request.getParameter("maxClassSize");
		String maxcoursesem = request.getParameter("maxCourseSem");
		   
		String databaseURL = "jdbc:mysql://localhost:3306/ARMS";
        String user = "arms";
        String password = "password";
        Connection conn = null;
        
        PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>Policy Details</title></head>");
		out.print("<style>body{width: 800px; margin: 20px auto; text-align: center;}</style>");
		out.print("<body><h2>Policy Details</h2><hr>");
		out.println("<table border=1 width=100% style=\"font-family:sans-serif; font-size:10pt;\">");
        out.println("<tr><th>PolicyId</th><th>MaxClassSize</th><th>MaxCourseSem</th><tr>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);
           
            PreparedStatement ips = conn.prepareStatement("UPDATE Policy SET MaxClassSize=?, MaxCourseSem=? WHERE PolicyId=0");
            ips.setString(1, maxclasssize);
            ips.setString(2, maxcoursesem);
            ips.executeUpdate();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Policy");
			ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	String upolicyid = rs.getString("PolicyId");
            	String umaxclasssize = rs.getString("MaxClassSize");
            	String umaxcoursesem = rs.getString("MaxCourseSem");
            	
            	out.println("<tr><td align=\"center\">" + upolicyid + "</td><td align=\"center\">" + umaxclasssize + "</td><td align=\"center\">" + umaxcoursesem + "</td>");
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
