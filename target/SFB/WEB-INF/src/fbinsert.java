import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

public class fbinsert extends HttpServlet
{
	
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
{
		
		              
		response.setContentType("text/html");
	


		
		PrintWriter out = response.getWriter();
		HttpSession hs;
		hs=request.getSession(true);

		String t1  = request.getParameter("ht11");
		String t2  = request.getParameter("ht21");
		String t11=(String)hs.getValue("a1");
		String t21=(String)hs.getValue("a2");

		String t12=(String)hs.getValue("test_sl");
		String t22=(String)hs.getValue("test_fn");
	
		out.println("<html><body>");
		out.println("<h2>"+t1+"</h2>");
		out.println("<h2>"+t2+"</h2>");
		out.println("<h2>"+t11+"</h2>");
		out.println("<h2>"+t21+"</h2>");
		out.println("<h2>"+t12+"</h2>");
		out.println("<h2>"+t22+"</h2>");
		
		out.println("</body></html>");
        
		
           

	
}
}








