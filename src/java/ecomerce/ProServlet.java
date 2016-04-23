/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce;

import static java.io.FileDescriptor.out;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static sun.misc.MessageUtils.out;

/**
 *
 * @author Douglas
 */
@WebServlet(name = "ProServlet", urlPatterns = {"/ProServlet"})
	public class  ProServlet extends HttpServlet 
	{
                @Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
		{ 
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body bgcolor=#CCCC99><center>");

		while(rs.next())
		{
		int k=1;
			out.println("<tr>");
			String j=rs.getString(k);															 
			String l=rs.getString(k+1);
			String m=rs.getString(k+2);
			
			out.println("<td><a href=http://localhost:8080/P1_Java/inscart?pro="+j+"&pros="+l+"&soop="+m+">"+j+"</a></td><td>"+l+"</td><td>"+m+"</td>");
			out.println("</tr>");
		}
		
}

                
    private static class rs {

        private static boolean next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static String getString(int k) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public rs() {
        }
    }
		}

