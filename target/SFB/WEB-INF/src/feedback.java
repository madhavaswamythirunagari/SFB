import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class feedback extends HttpServlet
{
	

	HttpSession hs;
	Connection conn= null;	
	Statement stmt= null;
	Statement stmt2= null;
	ResultSet rs=null;
	int c=0;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		
		HttpSession hs = req.getSession(true);
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String t_class=null,t_year=null,sid2=null,spwd2=null;
		String sid=(String)hs.getValue("sid");
		String spwd=(String)hs.getValue("spwd");
		sid2 =(String)hs.getValue("sid");
		spwd2=(String)hs.getValue("spwd");
	
		//int c=0;
		String srln;
		String ns[][]=new String[20][2];
		
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
				//------------------------------------------------------------------------------	
				String fs=new String("y");
				stmt=conn.createStatement();
				String Sq="select   *From STUDENTS_INFO where STUDENTID='"+sid+"' and PWD='"+spwd+"' ";
				
				rs = stmt.executeQuery(Sq);
				
				
				while(rs.next())
				{	
					t_class = rs.getString("BRANCH");
					t_year  = rs.getString("YEAR");
					hs.putValue("fclass",t_class);
					hs.putValue("fyear",t_year);

					if (fs.equals(rs.getString("FORMSUBMITT")))
					{
						hs=req.getSession(false);
						res.sendRedirect("errorpage.html");
					}
					
				}
				//----------------------------------------------------------------------------------
				 Sq="select *from Table1 where branch='"+t_class+"' and year ='"+t_year+"' ";
				 rs = stmt.executeQuery(Sq);
				
				 
				 c=0;
				 while(rs.next())
				{
					 ns[c][0]=rs.getString("FNAME");
					 ns[c][1]=rs.getString("SUBJECT");
					 
					 c=c+1;
				}
			}
			catch(Exception e){
				e.printStackTrace();}
	//Closing DBConnection 
		
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
						System.out.println("Feed back Before update Connection1 is Closed");
					}
				}
				catch(Exception e){
				e.printStackTrace();}
		//---------------------------------
			
				 
				 String grades[]={"Passion and Enthusiasm  to Teach",
										"Subject Knowledge,verbal expression and clarity of concepts", 
										"Motivating students, creating interest & Inviting Interaction", 
										"Discipline and Control over the Class", 
										"Regularity, Punctuality and Uniform Coverage of Syllabus", 
										"Quality of Illustrative, Visuals and Examples "}; 

			
		out.println("<html><head>");
					RequestDispatcher dispatcher = req.getRequestDispatcher("validate.js");
				    dispatcher.include(req, res);
		out.println("<title>Student FeedBack </title></head>");

		out.println("<BODY bgcolor=#FFF8DC>");
					out.println("<img src=aurora.jpg" );
					out.println("align = left");
					out.println("alt = CollegeLogo/>");
					out.println("<H1 ALIGN=CENTER>");
					out.println("<font  color=#00008B>Aurora's Engineering College </font><br> ");
					out.println("<font  color=#F08080> Student FeedBack Form </font><br>");
					out.println("<table><tr><th align=left><font color=#00008B>Class :"+t_class+"</th>");
					out.println("<th align=right><font color=#9932CC>Year :"+t_year+"</th></table>");
					out.println("</H1>");
					out.println("<hr width=100% />");
					
					
					out.println("<font size=3 color=#000080> Please give your feadback based on a 5 point scale described below</font></br>");
					out.println("<b><font size=4 color=#006400>5-Out Standing,</font></b>");
					out.println("<b><font size=4 color=#FF69B4> 4-Very Good,</font></b>");
					out.println("<b><font size=4 color=#1E90FF> 3-Good,</font></b>");
					out.println("<b><font size=4 color=#20B2AA>  2-Need Improvement,</font></b>");
					out.println("<b><font size=4 color=red>   1-Needs a Lot Improvement.</font></b>");

					out.println("<FORM name=sfeedback METHOD=post action='/SFB/fd' onSubmit='return validate(this)'>");
					out.println("<table border=1 align=center cellpadding=0 cellspacing=0 width=94% bgcolor=#FFFFFF>");
					out.println("<tr>");
					out.println("<th bgcolor=#FFB6C1 rowspan=3>Sl.<br>No.</th>");
					out.println("<th bgcolor=#FFB6C1 rowspan=3>Guidelines</th>");
					out.println("<th bgcolor=#AABECF colspan="+c+"> Subject Title/Teacher Name</th></tr>");

					//----------------------------------------------------------------------------------
					int i=0,j=0,s=1,g=0,r=1;
					int s_slno=1;
					String ht1="g1",ht2="g2",ht3="g3",ht4="g4",ht5="g5",ht6="g6",ht7="g7",ht8="g8",ht9="g9",ht10="g10",ht11="g11",ht12="g12",ht13="g13";
					out.println("<tr align=center>");
					
					out.println("<input type=hidden name=tsid2    value='"+sid2+"' > ");
					out.println("<input type=hidden name=tspwd2   value='"+spwd2+"' > ");
					out.println("<input type=hidden name=tclass   value='"+t_class+"' > ");
					out.println("<input type=hidden name=tyear    value='"+t_year+"' > ");
					out.println("<input type=hidden name=tc		  value='"+c+"' >");	
					out.println("<input type=hidden name=ccount   value='"+(c*2+6)+"' > ");
					while(i<c)
					{
					 ht2=ht2+i;
					 out.println("<input type=hidden name='"+ht2+"'   value='"+ns[i][1]+"' > ");
					 out.println("<th WIDTH=8% bgcolor=#FFEBCD>"+ns[i][1]+" </th>");
					 i=i+1;
					}
					out.println("</tr>");
					
					out.println("<tr align=center>");
					while(j<c)
					{
					ht3=ht3+j;
					out.println("<input type=hidden name='"+ht3+"'   value='"+ns[j][0]+"' > ");
					out.println("<th WIDTH=8% bgcolor=#F0E68C>"+ns[j][0]+"</th>");
					j=j+1;
					}
					out.println("</tr>");
				//-----------------------------------------------------------------------------------

							
		while(r<=6)
		 {
						out.println("<tr align=left>");
						out.println("<td WIDTH=5%  bgcolor=#E0FFFF>"+s+"</td>");
						out.println("<td WIDTH=15% bgcolor=#FDF5E6>"+grades[g]+"</td>");
				//----row 1
						 if (r==1)
						 {
						  i=0;
						 while(i<c)
						 {
							ht4=ht4+i;
							out.println("<td align=center><input type=text name='"+ht4+"'   size=1 maxlength=1>	</td>");
							i=i+1;

						 }
							
						 }
						 
				//----row 2

						 if (r==2)
						 {
						  i=0;
						 while(i<c)
						 {
							ht5=ht5+i;
							out.println("<td align=center><input type=text name='"+ht5+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 3
						 if (r==3)
						 {
						  i=0;
						 while(i<c)
						 {
							ht6=ht6+i;
							out.println("<td align=center><input type=text name='"+ht6+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 4
						 if (r==4)
						 {
						  i=0;
						 while(i<c)
						 {
							ht7=ht7+i;
							out.println("<td align=center><input type=text name='"+ht7+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 5
						 if (r==5)
						 {
						  i=0;
						 while(i<c)
						 {
							ht8=ht8+i;
							out.println("<td align=center><input type=text name='"+ht8+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 6
						 if (r==6)
						 {
						  i=0;
						 while(i<c)
						 {
							ht9=ht9+i;
							out.println("<td align=center><input type=text name='"+ht9+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 7
				/*
						 if (r==7)
						 {
						  i=0;
						 while(i<c)
						 {
							ht10=ht10+i;
							out.println("<td align=center><input type=text name='"+ht10+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 8
						 if (r==8)
						 {
						  i=0;
						 while(i<c)
						 {
							ht11=ht11+i;
							out.println("<td align=center><input type=text name='"+ht11+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 9
						 if (r==9)
						 {
						  i=0;
						 while(i<c)
						 {
							ht12=ht12+i;
							out.println("<td align=center><input type=text name='"+ht12+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }
				//----row 10
						 if (r==10)
						 {
						  i=0;
						 while(i<c)
						 {
							ht13=ht13+i;
							out.println("<td align=center><input type=text name='"+ht13+"'   size=1 maxlength=1>	</td>");
							i=i+1;
						 }
						 }

				*/	
						 out.println("</tr>");
						 r=r+1;
						 s=s+1;
						 g=g+1;
		}
				out.println("</table>");
				out.println("<center>");
				out.println("<INPUT TYPE=SUBMIT align=center   value =Submitt>");
				out.println("</form>");
				out.println("</center>");
				
				//srln=Integer.toString(c);
				//hs.putValue("srlno",srln);	
				out.println("</body></html>");

			//---------------------------------------------------------------------

		


							
				
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
			int sno=0;	
			String tsno;
			int cform=0;
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			HttpSession hs = req.getSession(true);
			
			String fc=(String)hs.getValue("fclass");
			String fy=(String)hs.getValue("fyear");
			//String srno=(String)hs.getValue("srlno");
			String sid=(String)hs.getValue("sid");
			String spwd=(String)hs.getValue("spwd");
			//String shat2=(String)hs.getValue("shat");

			System.out.println("update = "+sid);
//			System.out.println("update = "+spwd);
//			System.out.println("update = "+shat2);
			sno=c;
			int shat=0;
				
				if( sid==null || spwd==null || sno==0)
				{
				 sid  = req.getParameter("tsid2");
			     spwd = req.getParameter("tspwd2");
				 fc   = req.getParameter("tclass");
				 fy   = req.getParameter("tyear");
				 tsno  = req.getParameter("tc");
				 sno=Integer.parseInt(tsno);	
				}

			  

			int i=0;
// Previous Form 
					String ht2="g2",ht3="g3",ht4="g4",ht5="g5",ht6="g6",ht7="g7",ht8="g8",ht9="g9";//,ht10="g10",ht11="g11",ht12="g12",ht13="g13";
// New  form
					String ft2,ft3,ft4,ft5,ft6,ft7,ft8,ft9;//ft10,ft11,ft12,ft13;
					
					int gt4,gt5,gt6,gt7,gt8,gt9;//,gt10,gt11,gt12,gt13;
					String fs=new String("y");
					int up=0;
					//------------------------------------------------------------------------------							
							
							try
							{
								Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
								conn=DriverManager.getConnection("jdbc:odbc:aec","Vinayaka","Vinayaka");
								
								stmt2=conn.createStatement();
								String Sq="select   *From STUDENTS_INFO where STUDENTID='"+sid+"' and PWD='"+spwd+"' ";
				
								rs = stmt2.executeQuery(Sq);
				
								while(rs.next())
								{	
								if (fs.equals(rs.getString("FORMSUBMITT")))
								{
									System.out.println(" Status in get"+fs);
									up=1;
									res.sendRedirect("errorpage.html");
								}
					
								}
							}
							catch(Exception e){
							e.printStackTrace();}
					//-----------------------------------------------------------------------------	
			if (up==0 && sid!=null)
			{
			while (i<sno)
			{			System.out.println("This loop in"+i+"times"+" "+sid);
						ht2=ht2+i; ht3=ht3+i; ht4=ht4+i; ht5=ht5+i; ht6=ht6+i;
						ht7=ht7+i; ht8=ht8+i; ht9=ht9+i;// ht10=ht10+i; ht11=ht11+i; ht12=ht12+i; ht13=ht13+i;

						
						ft2 = req.getParameter(ht2); ft3 = req.getParameter(ht3); ft4 = req.getParameter(ht4);
						ft5 = req.getParameter(ht5); ft6 = req.getParameter(ht6); ft7 = req.getParameter(ht7);
						ft8 = req.getParameter(ht8); ft9 = req.getParameter(ht9); /*ft10 = req.getParameter(ht10);
						ft11 = req.getParameter(ht11);ft12 = req.getParameter(ht12);ft13 = req.getParameter(ht13);*/

						gt4=Integer.parseInt(ft4); gt5=Integer.parseInt(ft5); gt6=Integer.parseInt(ft6);
						gt7=Integer.parseInt(ft7); gt8=Integer.parseInt(ft8); gt9=Integer.parseInt(ft9);
						/*gt10=Integer.parseInt(ft10); gt11=Integer.parseInt(ft11); gt12=Integer.parseInt(ft12);
						gt13=Integer.parseInt(ft13);*/
						float tot=((float)(gt4+gt5+gt6+gt7+gt8+gt9))/6;
						 
						 try
							{
								stmt2=conn.createStatement();	
								//if (shat3==1)
								//{
								//	stmt2.executeUpdate( "Insert into att_low values("+i+",'"+ft3+"','"+ft2+"','"+fc+"','"+fy+"',"+gt4+","+gt5+","+gt6+","+gt7+","+gt8+","+gt9+","+tot+")");	
								//}
								//else
								//{
									stmt2.executeUpdate( "Insert into SUB_DET values("+i+",'"+ft3+"','"+ft2+"','"+fc+"','"+fy+"',"+gt4+","+gt5+","+gt6+","+gt7+","+gt8+","+gt9+","+tot+")");	
								//}
							}
							catch(Exception e){
							e.printStackTrace();}
			i=i+1;
			}
							try
							{
								
									
									stmt2.executeUpdate("update STUDENTS_INFO set formsubmitt='"+fs+"' where STUDENTID='"+sid+"' and pwd='"+spwd+"' ");  
									cform=1;
								
							}
							catch(Exception e){
							e.printStackTrace();}
						

			}
	

							/*try
							{
								if(sid!=null)
								{
									stmt2=conn.createStatement();	
									stmt2.executeUpdate("update STUDENTS_INFO set formsubmitt='"+fs+"' where STUDENTID='"+sid+"' and pwd='"+spwd+"' ");  
								}
							}
							catch(Exception e){
							e.printStackTrace();}*/
							try{}
		finally
				{
			try
				{
					if(rs!=null)
					{
					 rs.close();
					 System.out.println("Feed  back rs is Closed");
					}
					
					if(stmt!=null)
					{
					 stmt.close();
					 System.out.println("Feed  back statement is Closed");
					}
					if(stmt2!=null)
					{
					 stmt2.close();
					 System.out.println("Feed  back statement is Closed");
					}
					
					if(conn!=null)
					{
					conn.close();
					System.out.println("Feed back update Connection2 Closed");
					}
					hs.invalidate();
				}
				catch(Exception e){
				e.printStackTrace();}
				}
							




					if(cform==1)
						{
						 //res.sendRedirect(res.encodeRedirectUrl("/SFB/ifstr"));
						hs=req.getSession(false); 
						out.println("<html><body background=#FFE87C>");
						//out.println("<BODY background=	#FFE87C>");
						out.println("<img src=aurora.jpg" );
						out.println("align = left");
						out.println("alt = CollegeLogo/>");
						out.println("<H1 ALIGN="+"CENTER>");
						out.println("<font size20 color="+"purple>"+" Aurora's Engineering College </font> <br />");
						out.println("<font size20 color="+"orange>"+" Student FeedBack </font>");
						out.println("<p> <hr />");
						out.println("</H1>");
						out.println("<p>");
						out.println("<center><font size=8 color=#F08080> ThanQ for your valuable feedback</font><br><br>");
						out.println("</body></html>");
						cform=0;
						}


			
						
	}

}

  
