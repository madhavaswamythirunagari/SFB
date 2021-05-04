import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class feedback extends HttpServlet
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
		
		String t_class = request.getParameter("h_class");
		String t_year  = request.getParameter("h_year");
	
		out.println("<html><body>");
		//out.println("<h2>"+t_class+"</h2>");
		//res.sendRedirect("http://localhost:8080/tectest1/Adminsetup.html");
		String Sq="select *from Table1 where branch='"+t_class+"' and year ='"+t_year+"' ";
		rs = stmt.executeQuery(Sq);

      
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
					
					out.println("<font size=5>");
					out.println("<form method=POST action='/SFB/fd'>");	
					
					out.println("<table border='"+2+"' cellpading='"+6+"' cellspacing='"+3+"'>");
					out.println("<tr bgcolor=navyblue>");
  					out.println("<th> Sl.N0 </th>");
					out.println("<th> Faculty Name </th>");
					out.println("<th> Subject </th>");
					out.println("<th> Passion and Enthusisum  to Teach</th>");
					out.println("<th> Subject Knowledge </th>");
					out.println("<th> Clarity and Emophasis on Concepts </th>");
					out.println("<th> Motivating and Inspiring the Studen </th>");
					out.println("<th> Creating Interest in the Subject </th>");
					out.println("<th> Quality on Illustrative Visuals, Examples and Applications </th>");
					out.println("<th> Regularity, Punctuality and Uniform Coverege of Syllabus </th>");
					out.println("<th> Discipline and control over the class </th>");
					out.println("<th> Promoting Student Thinking </th>");
					out.println("<th> Encouraging Student effort and Inviting Student Interaction </th> </tr>");
					out.println("</font>");

			 int s_slno=1;
			 String ht1="g1";
			 String ht2="g2";
			 String ht3="g3";
			 String ht4="g4";
			 String ht5="g5";
			 String ht6="g6";
			 String ht7="g7";
			 String ht8="g8";
			 String ht9="g9";
			 String ht10="g10";
			 String ht11="g11";
			 String ht12="g12";
			 String ht13="g13";



					while(rs.next())
					 {
						
					String s_faculty= rs.getString("fname");
					String s_subject= rs.getString("subject");
					/*out.print("<h4>"+s_slno+"</h4>");
					out.print("<h4>"+s_faculty+"</h4>");
					out.print("<h4>"+s_subject+"</h4>");*/
		
					ht1=ht1+s_slno;
					ht2=ht2+s_slno;
					ht3=ht3+s_slno;
					ht4=ht4+s_slno;
					ht4=ht4+s_slno;
					ht5=ht5+s_slno;
					ht6=ht6+s_slno;
					ht7=ht7+s_slno;
					ht8=ht8+s_slno;
					ht9=ht9+s_slno;
					ht10=ht10+s_slno;
					ht11=ht11+s_slno;
					ht12=ht12+s_slno;
					ht13=ht13+s_slno;

					
					out.println("<tr valign=top>");
					out.println("<td><input type=text name='"+ht1+"'   value='"+s_slno+"' size=2>	</td>");
					out.println("<td><input type= name='"+ht2+"'   value='"+s_faculty+"' size=15>	</td>");
					out.println("<td><input type=text name='"+ht3+"'   value='"+s_subject+"' size=15>	</td>");
					out.println("<td><input type=text name='"+ht4+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht5+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht6+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht7+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht8+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht9+"'   size=4>	</td>");
					out.println("<td><input type=text name='"+ht10+"'  size=4>  </td>");
					out.println("<td><input type=text name='"+ht11+"'  size=4>	</td>");
					out.println("<td><input type=text name='"+ht12+"'  size=4>	</td>");
					out.println("<td><input type=text name='"+ht13+"'  size=4>  </td>");
			
					out.println("</tr>");
					
					s_slno=s_slno+1;
					}
		out.println("</table>");
		out.println("<INPUT TYPE=SUBMIT value =Submitt>");
		out.println("</form>");
		out.println("</body></html>");
        
		 rs.close();
         stmt.close();
         conn.close();
           
	    }
	catch(Exception e)
	{
	}
}


public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
     {
try{
res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<html><body>");

String cid1=req.getParameter("ht11");
System.out.println("slno  "+cid1);
String cid2=req.getParameter("ht21");
System.out.println("F_Name "+cid2);
out.println("<h1>");
out.println(cid1);
out.println(cid2);
out.println("</h1>");
out.println("</body></html>");
}
catch(Exception e){	e.printStackTrace();};
	 }
}








/*pw.println("<html><body background=Ripple.jpg><font color=blue><h2><center>Select  Exam For an Existing  Course</center> </h2><hr color='orange'><form method=POST action='Examination'><br><br><table align='center'><tr><td><b>Choose Course ID:<select name='CID'> ");
	try{
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select course_id from courses1 order by 1");
	while( rs.next())
	pw.println("<option>" + rs.getString(1) + "<br>" );
	//rs.close();
	//st.close();
	pw.println("</select></b></td></tr><tr><td><input type=submit  value='--Select--'></td><tr></table></form></font></body></html>"); */