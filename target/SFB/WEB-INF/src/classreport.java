import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class classreport extends HttpServlet
{
	

	
	Connection conn= null;	
	Statement stmt= null;
	ResultSet rs=null;
	
	
	public void init(ServletConfig sc)throws ServletException
	{
    super.init(sc);
		try
	    {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
		 }   
		catch(Exception e){
		e.printStackTrace();}
	}  


	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		
		
	
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String t_class = req.getParameter("h_class");
		String t_year  = req.getParameter("h_year");

		
		System.out.println("Class "+t_class);
		System.out.println("Year  "+t_year);
		
		
			try
			{
				
				stmt=conn.createStatement();	
				String Sq="SELECT SUB_DET.FNAME, SUB_DET.SUBJECT,count(*) as numst, avg( SUB_DET.[TOT-PER]) as per FROM SUB_DET where class='"+t_class+"'  and year='"+t_year+"' group  by fname, subject";
				
				rs  = stmt.executeQuery(Sq);

				
		
		out.println("<html><body>");
			//out.println("<BODY background=Ripple.jpg>");
			  out.println("<BODY bgcolor=navyblue>");
					out.println("<img src=aurora.jpg" );
					out.println("align = left");
					out.println("alt = CollegeLogo/>");
					out.println("<H1 ALIGN="+"CENTER>");
					out.println("<font size20 color="+"purple>"+" Aurora's Engineering College </font> <br />");
					out.println("<font size20 color="+"yellow>"+" Student FeedBack Form </font>");
					out.println("<p> <hr />");
					out.println("</H1>");
					out.println("<p>");
					
					out.println("<H2 ALIGN="+"CENTER>");
					out.println("<font size15 color="+"blue>"+" Class :"+t_class+" </font> <br />");
					out.println("<font size15 color="+"yellow>"+" Year  :"+t_year+"</font>");
					out.println("</H2>");

					
					out.println("<H3 align=center>");				
					
					out.println("<table border='"+2+"' cellpading='"+6+"' cellspacing='"+3+"'>");
					out.println("<tr bgcolor=lightpink>");
  					out.println("<th> Sl.N0 </th>");
					out.println("<th> Faculty Name </th>");
					out.println("<th> Subject </th>");
					out.println("<th> No. Of Students </th>");
					out.println("<th> Total Percentage</th></tr>");


			 int s_slno=1;
			 String ht1="g1";
			 String ht2="g2";
			 String ht3="g3";
			 String ht4="g4";
			 String ht5="g5";
			 
					float totper;
					int   totstu;
					
					while(rs.next())
					 {
						
					String s_faculty= rs.getString("fname");
					String s_subject= rs.getString("subject");
					totper=rs.getFloat("per");
					totstu=rs.getInt("numst");
					System.out.println("faculty name"+s_faculty);
					System.out.println("faculty name"+s_subject);
					ht1=ht1+s_slno;
					ht2=ht2+s_slno;
					ht3=ht3+s_slno;
					ht4=ht4+s_slno;
					ht5=ht5+s_slno;
									

					
					out.println("<tr bgcolor=white valign=top>");
					out.println("<td>"+s_slno+"	    </td>");
					out.println("<td>"+s_faculty+"	</td>");
					out.println("<td>"+s_subject+"	</td>");
					out.println("<td>"+totstu+" 	</td>");
					out.println("<td>"+totper+"  	</td>");
					out.println("</tr>");
					
					s_slno=s_slno+1;
					
					}
					out.println("</table>");
					
				
				out.println("</body></html>");
				

			}
				catch(Exception e){
				e.printStackTrace();}

	}
}