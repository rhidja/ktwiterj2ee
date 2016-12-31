package ktwtr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rhidja
 */
public class Home extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
        String message = "Transmission de variables : OK !";
        request.setAttribute( "name", message );
        this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
    }
}