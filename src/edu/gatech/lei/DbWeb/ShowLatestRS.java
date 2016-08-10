package edu.gatech.lei.DbWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowLatestRS
 */
@WebServlet("/ShowLatestRS")
public class ShowLatestRS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	response.setContentType("text/html");	
	 PrintWriter out = response.getWriter();
	
	
	String databaseURL = "jdbc:mysql://localhost:3306/ARMS";
    String user = "arms";
    String password = "password";
    Connection conn = null;
       
	out.print("<html>");
	out.print("<head><title>Admin: Summary Report</title></head>");
	out.print("<style>body{width: 1000px; margin: 50px auto; }</style>");
	out.print("<body><h2>Summary Report</h2><hr>");
    out.println("<table border=1 style=\"font-family:sans-serif; font-size:10pt;\">");
    out.println("<tr><th>RequestID</th><th>StudentID</th><th>CourseID</th><th>TermID</th><th>SCHEDULE</th><tr>");
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(databaseURL, user, password);
        PreparedStatement ips = conn.prepareStatement(
        		"SELECT t1.RequestId, t1.StudentId, t1.CourseId, t1.TermId, t2.Schedule FROM CourseRequests AS t1 INNER JOIN Schedules AS t2 ON t1.RequestId = t2.RequestId WHERE t1.StudentId =2 ORDER BY t1.TimeStamp DESC LIMIT 5 ");
		ResultSet irs = ips.executeQuery();
        while(irs.next()){
        	String grequestid = irs.getString("RequestId");
        	String gstudentid = irs.getString("StudentId");
        	String gcourseid = irs.getString("CourseId");
        	String dtermid = irs.getString("TermId");
        	String gscheduleid = irs.getString("Schedule");
        	out.println("<tr><td align=\"center\">" + grequestid + "</td><td align=\"center\">" + gstudentid + "</td><td align=\"center\">" + gcourseid + "</td><td align=\"center\">" + dtermid + "</td><td align=\"center\">" + gscheduleid + "</td></tr>");
        }
        out.println("</table>");
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
	out.println("</html></body>");

}
}
