import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class resetpwd extends HttpServlet
{
	

	HttpSession hs;
	Connection conn= null;	
	Statement stmt= null;

	
		
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		int test=0;
		int atest=0;
		
	
		HttpSession hs = req.getSession(true);
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String sid   = (String)hs.getValue("sid");
		String spwd  = req.getParameter("pwd2");
		sid=sid.trim();
		spwd=spwd.trim();
		System.out.println("password id"+sid);		
			try
		    {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
										
				stmt=conn.createStatement();
				//-------------Student Password Updation-----------------------
				int rp=1;
//				UPDATE STUDENTS_INFO SET STUDENTS_INFO.pwd_reset = 1 WHERE (((STUDENTS_INFO.STUDENTID)="04621A1205"));

				stmt.executeUpdate( "update STUDENTS_INFO set PWD='"+spwd+"' , pwd_reset="+rp+" where STUDENTID='"+sid+"' " );	
	
			}
			catch(Exception e){
				e.printStackTrace();}

			
				//--------Closeing the DataBase Connection
				try
				{
					
					if (stmt!=null)
					{
						stmt.close();
						System.out.println("Reset password stmt closed");
					}
					if (conn!=null)
					{
						conn.close();
						System.out.println("Reset password closed");
					}
				}
					catch(Exception e){
						e.printStackTrace();}


		//------------------------------------------	
			res.sendRedirect("studentlogin.html");
	}
			
}
