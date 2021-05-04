import java.io.*;
import java.util.Random;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class slogin extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
{
		Connection conn= null;
		Statement stmt= null;
		ResultSet rs=null;
		
		
		 	
		//String trno =request.getParameter("rno");	
              
		response.setContentType("text/html");
	
	try
	{	

		PrintWriter out = response.getWriter();

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
	    System.out.println("Connection Established");

		stmt=conn.createStatement();
	
		out.println("<html><body>");

		/*out.println("<BODY background ="+"\\\\sridhar\\photo\\bg2.jpg>");
		out.println("<img src="+"\\\\sridhar\\photo\\aurora.jpg" );
		out.println("align = left");
		out.println("alt = CollegeLogo/>");
		//out.println("<img src='"+sp+"'" );
		out.println("align = right");
		out.println("width='"+80+"'");
        out.println("height='"+80+"'");
		out.println("alt = CollegeLogo/>");
		out.println("<H1 ALIGN="+"CENTER>");*/
		out.println("<font size20 color="+"purple>"+" Aurora's Engineering College </font> <br />");
		out.println("<font size20 color="+"purple>"+" Online Exam </font>");
		out.println("<p> <hr />");
		out.println("</H1>");
		out.println("<p>");

			        
					
					String s_fname   =request.getParameter("f_fname");	
					String s_subject =request.getParameter("f_subject");	
					String s_branch  =request.getParameter("f_branch");	
					String s_year    =request.getParameter("f_year");	
					stmt.executeUpdate("insert into Table1 values(s_fname,s_subject,s_branch,s_year)");
	
		out.println("</body></html>");
        
			rs.close();
            stmt.close();
            conn.close();
           
	    }
	catch(Exception e)
	{
	}
}
}