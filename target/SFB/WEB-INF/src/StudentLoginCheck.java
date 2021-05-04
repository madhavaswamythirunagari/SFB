import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class StudentLoginCheck extends HttpServlet
{
	

	HttpSession hs;
	Connection conn= null;	
	Statement stmt= null;
	ResultSet rs=null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		int test=0;
		int atest=0;
		int rpw=0;
		int shatt=0;
		
		
		HttpSession hs = req.getSession(true);
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String sid = req.getParameter("studid");
		String spwd  = req.getParameter("password");
		sid=sid.trim();
		spwd=spwd.trim();
				
			try
		    {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
				stmt=conn.createStatement();

				//-------------Student login check-----------------------
				String Sq="select   *From STUDENTS_INFO where STUDENTID='"+sid+"' and PWD='"+spwd+"' ";
				
				rs = stmt.executeQuery(Sq);
				
				while(rs.next())
				{	
					String user= rs.getString("STUDENTID");
					String pwd= rs.getString("PWD");
				    rpw=rs.getInt("pwd_reset");
					shatt=rs.getInt("short_att");
					user=user.trim();
					pwd=pwd.trim();
					String sh=Integer.toString(shatt);
					if (sid.equals(user) && spwd.equals(pwd))
					{
					System.out.println(" Student login Success"+sid);
					test=1;
					hs.putValue("sid",sid);
					hs.putValue("spwd",spwd);
					hs.putValue("shat",sh);
					}
				}
				

				//-------------Admin login check-----------------------

				Sq="select   *From users where username='"+sid+"' and password='"+spwd+"' ";
				rs = stmt.executeQuery(Sq);
				
				while(rs.next())
				{	
					String user= rs.getString("username");
					String pwd= rs.getString("password");
					user=user.trim();
					pwd=pwd.trim();
					if (sid.equals(user) && spwd.equals(pwd))
					{
					hs.putValue("login","yes");
					System.out.println(" Admin  login Success"+sid);
					atest=1;
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();}
				
		//--------Closeing the DataBase Connection----------------------------------
				try
				{
					if (rs!=null)
					{
						rs.close();
						System.out.println("student login rs closed");
					}
					if (stmt!=null)
					{
						stmt.close();
						System.out.println("student login stmt closed");
					}
					if (conn!=null)
					{

						conn.close();
						System.out.println("student login connection closed");
					}
				}
					catch(Exception e){
						e.printStackTrace();}

			
		//-------------Reset password-----------------------------------------------
					
					/*if (test==1 && rpw==0  )
					{
						res.sendRedirect("resetpwd.html");
					}*/
		
		//----------Admin page---------------------------------------------		
				
				if (atest==1)
				{
					atest=0;	
					res.sendRedirect("mainpage.html");
				}	
		//--------Student page-----------------------------------------------				
			if (test==0)
			{	
					
					out.println("<HTML>");
					out.println("<head><title>Student Feedback</title></head>");
					out.println("<BODY background=Ripple.jpg>");
					out.println("<img src=aurora.jpg  align =left alt=College Logo />");
					out.println("<H1 ALIGN=CENTER>");
					out.println("<font size20 color=blue> Aurora's Engineering College </font> <br />");
					out.println("<font size20 color=yellow> Students Feed Back Form</font><br />");
					out.println("<p> <hr />");
					out.println("</H1>");
					out.println("<center><font size=10 color=yellow>Enter Correct User Name or Password </font><br />");
					out.println("<a href=studentlogin.html><font size=8 color=white>Back</font></center></a>");
					out.println("</body>");
					out.println("</html>");
			}
			else 
			{
					if (rpw==0  )
					{
						res.sendRedirect("resetpwd.html");
					}
					else
					{
					 test=0;
					 res.sendRedirect(res.encodeRedirectUrl("/SFB/fd"));
					  //res.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/SFB/fd");
					}
			}
			

		//--------Finally Closeing the DataBase Connection---------------------
		
		/*finally
		{
			try
				{
					if (rs!=null)
					{
						rs.close();
						System.out.println("student login rs closed");
					}
					if (stmt!=null)
					{
						stmt.close();
						System.out.println("student login stmt closed");
					}
					if (conn!=null)
					{

						conn.close();
						System.out.println("Finally student login connection closed");
					}
				}
					catch(Exception e){
						e.printStackTrace();}
		}*/
	//-----------End Finally-------------------------------	
	}
			
}
