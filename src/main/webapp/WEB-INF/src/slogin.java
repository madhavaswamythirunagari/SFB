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
		
		
		 	
		
              
		response.setContentType("text/html");
	
	try
	{	

		PrintWriter out = response.getWriter();

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
	   

		stmt=conn.createStatement();
	
		out.println("<html><body>");
		
		String Sq="select *from Table1";
        rs = stmt.executeQuery(Sq);

    
		
			        //out.println("<h1> Record Updated </h1>");
					int  s_sno=1;
					String s_fname   =request.getParameter("f_fname");	
					String s_subject =request.getParameter("f_subject");	
					String s_branch  =request.getParameter("f_branch");	
					String s_year    =request.getParameter("f_year");
					//out.println("<h2>'"+s_fname+"'</h2>");

					stmt.executeUpdate("insert into Table1(slno,fname,subject,branch,year) values("+s_sno+",'"+s_fname+"','"+s_subject+"','"+s_branch+"','"+s_year+"')");
					
					//out.println("<html><body>");
					out.println("<BODY background=Ripple.jpg>");
					out.println("<img src=aurora.jpg" );
					out.println("align = left");
					out.println("alt = CollegeLogo/>");
					out.println("<H1 ALIGN="+"CENTER>");
					out.println("<font size20 color="+"purple>"+" Aurora's Engineering College </font> <br />");
					out.println("<font size20 color="+"yellow>"+" Student FeedBack Form </font>");
					out.println("<p> <hr />");
					out.println("</H1>");
					out.println("<p>");
					out.println("<center>");
					out.println("<font size=10 color="+"yellow>"+" Record Updated Successfully  </font><br />");
					out.println("<a href=login.html><font size=8 color=white>Back</a></font>");
					out.println("</center>");
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