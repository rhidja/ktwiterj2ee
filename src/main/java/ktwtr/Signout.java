package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rhidja
 */
public class Signout extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
    	HttpSession session = request.getSession(true);
        session.invalidate();
        
    	this.getServletContext().getRequestDispatcher( "/signin.jsp" ).forward( request, response );
    }
}