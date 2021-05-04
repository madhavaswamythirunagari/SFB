import java.io.*;
import java.sql.*;
import java.text.*;
import  java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class results3 extends HttpServlet
{
	

	HttpSession hs;
	Connection conn= null;	
	Statement stmt= null;
	ResultSet rs=null;
	int test=0;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		HttpSession hs = req.getSession(true);


		String t_class = req.getParameter("h_class");
		String t_year  = req.getParameter("h_year");
		String login=(String)hs.getValue("login");

		
		System.out.println("Class "+t_class);
		System.out.println("Year  "+t_year);
		
		if(login.equals("yes"))
			System.out.println("Login Status is Ok");
		else
		  res.sendRedirect("studentlogin.html");

		int c=0;
		float n[][]=new float[20][20];
	    String ns[][]=new String[20][2];
		int totnumst=0;
		 
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
				stmt=conn.createStatement();	
				  
				String step1="delete *from tot_stud";	
				String step2="insert into tot_stud select *from SUB_DET";
				String step3="insert into tot_stud select *from att_low";

				stmt.executeUpdate(step1);
				stmt.executeUpdate(step2);
				stmt.executeUpdate(step3);
				
				String Sq="SELECT FNAME,SUBJECT, avg(g1) AS gd1,avg(g2) AS gd2,avg(g3) AS gd3, avg(g4) AS gd4, avg(g5) AS gd5, avg(g6) AS gd6, count(*) AS numst ,avg([TOT-PER]) AS per FROM tot_stud where class='"+t_class+"'  and year='"+t_year+"' GROUP BY fname, subject";
				
				rs  = stmt.executeQuery(Sq);
				

				
				while(rs.next())
					 {  
						    n[c][0]=rs.getFloat("gd1");
							n[c][1]=rs.getFloat("gd2");
							n[c][2]=rs.getFloat("gd3");
							n[c][3]=rs.getFloat("gd4");
							n[c][4]=rs.getFloat("gd5");
							n[c][5]=rs.getFloat("gd6");
							/*n[c][6]=rs.getFloat("gd7");
							n[c][7]=rs.getFloat("gd8");
							n[c][8]=rs.getFloat("gd9");
							n[c][9]=rs.getFloat("gd10");*/
							//System.out.println("data is"+n[c][0]);
							//------------------------------------
							ns[c][0]=rs.getString("FNAME");
							ns[c][1]=rs.getString("SUBJECT");
							totnumst=rs.getInt("numst");
						
						c=c+1;
						
					 }
			
			}
				catch(Exception e){
				e.printStackTrace();}



				 System.out.println("Row Count is"+c);
				// rs  = stmt.executeQuery(Sq);
						
					 String grades[]={"Passion and Enthusiasm  to Teach","Subject Knowledge,verbal expression and clarity of concepts", 
										"Motivating students, creating interest & Inviting Interaction", 
										"Discipline and Control over the Class", 
										"Regularity, Punctuality and Uniform Coverage of Syllabus", 
										"Quality of Illustrative Visuals and Examples"}; 

	





		out.println("<html><body>");
			
			  out.println("<BODY bgcolor=navyblue>");
					out.println("<img src=aurora.jpg" );
					out.println("align = left");
					out.println("alt = CollegeLogo/>");
					out.println("<H1 ALIGN=CENTER>");
					out.println("<font size=6 color=purple>Aurora's Engineering College</font><br/>");
					out.println("<font size=6 color=yellow>Student FeedBack Report</font>");
					out.println("</H1>");
					out.println("<hr />");
					
					//out.println("<p>");
					
					/*out.println("<H2 ALIGN="+"CENTER>");
					out.println("<font size15 color="+"blue>"+" Class :"+t_class+" </font> <br/>");
					out.println("<font size15 color="+"yellow>"+" Year  :"+t_year+"</font>");
					out.println("</H2>");*/

					
					out.println("<b><font size=4 color=#FDF5E6>"+"&nbsp;&nbsp;&nbsp;&nbsp;Branch:&nbsp;&nbsp;"+t_class+"&nbsp;&nbsp;&nbsp;Year:&nbsp;"+t_year+"&nbsp;&nbsp;&nbsp;&nbsp;Number of Students Present:&nbsp;"+totnumst+"</font></b><br>");
					out.println("<table border=1  cellpadding=0 cellspacing=0 width=94% bgcolor=#FFFFFF>");
					//out.println("<H3 align=center>");				

					 out.println("<tr>");
					 out.println("<th bgcolor=#FFB6C1 rowspan=3>Sl.<br>No.</th>");
					 out.println("<th bgcolor=#FFB6C1 rowspan=3>Guidelines</th>");
					 out.println("<th bgcolor=#AABECF colspan="+c+"> Subject Title/Teacher Name</th></tr>");
//----------------------------------------------------------------------------------
					int i=0,j=0,s=1,g=0,r=0,tots;
					float totp,tot=0;
					out.println("<tr align=center>");
					while(i<c)
					{
					out.println("<td WIDTH=8% bgcolor=#FFEBCD><b>"+ns[i][1]+"</b></td>");
					i=i+1;
					}
					out.println("</tr>");
					
					out.println("<tr align=center>");
					while(j<c)
					{
					out.println("<td WIDTH=8% bgcolor=#F0E68C><b>"+ns[j][0]+"</b></td>");
					j=j+1;
					}
					out.println("</tr>");
//-----------------------------------------------------------------------------------
					
					
					while(r<6)
					 {
						out.println("<tr align=center>");
						out.println("<td WIDTH=3%  bgcolor=#E0FFFF>"+s+"</td>");
						out.println("<td align=left WIDTH=15% bgcolor=#FDF5E6>"+grades[g]+"</td>");
						 i=0;
						 while(i<c)
						 {
							 out.println("<td>"+n[i][r]+"</td>");
							 i=i+1;
						 }
						 out.println("</tr>");
						 r=r+1;
						 s=s+1;
						 g=g+1;
					 }
//---------------------------------------------------------------------
					r=0;
					//tots=rs.getInt("numst");
					out.println("<tr align=center>");
					out.println("<td WIDTH=5%  bgcolor=#00BFFF></td>");
					out.println("<td WIDTH=15% bgcolor=#00BFFF>Average</td>");
					while(r<c)
					{
						i=0;
						float tt=0;
						
						while(i<1)
						{
							tt=tt+n[r][0]*(float)0.15+n[r][1]*(float)0.3+n[r][2]*(float)0.15+n[r][3]*(float)0.15+n[r][4]*(float)0.1+n[r][5]*(float)0.15;
							i=i+1;
						}
						
						DecimalFormat df = new DecimalFormat("#.000");
						
						
						
						out.println("<td bgcolor=#00BFFF><b>"+df.format(tt)+"</b></td>");
						r=r+1;
					}
					out.println("</tr>");



				out.println("</table>");
				//out.println("<center><a href=result.html><font size=6 color=white>Back</a></font></center>");
				out.println("</body></html>");
				

			/*}
				catch(Exception e){
				e.printStackTrace();}*/


				//Closing DBConnections

				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(stmt!=null)
					{
						stmt.close();
					}
					if(conn!=null)
					{
						conn.close();
						System.out.println("Results Connection is Closed");
					}
				}
				catch(Exception e){
				e.printStackTrace();}

	
}

}