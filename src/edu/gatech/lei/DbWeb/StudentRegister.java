package edu.gatech.lei.DbWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentRegister")
public class StudentRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    String susername = request.getParameter("susername");
		String spassword = request.getParameter("spassword");
		String databaseURL = "jdbc:mysql://localhost:3306/ARMS";
        String user = "arms";
        String password = "password";
        Connection conn = null;
        
        PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>Admin: System Users</title></head>");
		out.print("<style>body{width: 800px; margin: 20px auto; text-align: center;}</style>");
		out.print("<body><h2>System Users</h2><hr>");
		out.println("<table border=1 width=100% style=\"font-family:sans-serif; font-size:9pt;\">");
        out.println("<tr><th>UserName</th><th>Password</th><tr>");
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);           
            PreparedStatement ips = conn.prepareStatement("INSERT INTO Users (UserName, Password) VALUES (?,?)");
            ips.setString(1, susername);
            ips.setString(2,spassword);
            ips.executeUpdate();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	String nusername = rs.getString("UserName");
            	String npassword = rs.getString("Password");

            	out.println("<tr><td align=\"center\">" + nusername + "</td><td align=\"center\">" + npassword + "</td></tr>");
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
