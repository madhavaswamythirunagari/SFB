import java.io.*;
import java.text.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;


public class fdmainrp extends HttpServlet
{
	

	HttpSession hs;
		
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
		
		HttpSession hs = req.getSession(true);
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String t_rtype  = req.getParameter("rtype");
		t_rtype=t_rtype.trim();
					
		
					if(t_rtype.equals("tp1"))
					{
					RequestDispatcher dispatcher =   req.getRequestDispatcher("/fdrp1");
					dispatcher.forward(req, res);
					}

					if(t_rtype.equals("tp2"))
					{
					RequestDispatcher dispatcher =   req.getRequestDispatcher("/fdrp2");
					dispatcher.forward(req, res);
					}

					
		
					
		}
}	
