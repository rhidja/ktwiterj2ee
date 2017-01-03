package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avaje.ebean.Ebean;

import ktwtr.models.Member;

/**
 *
 * @author rhidja
 */
@WebServlet( "/signup" )
public class Signup extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/signup.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    	HttpSession session = request.getSession(true);
    	
    	if( session.getAttribute("member") != null) {
    		this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
    	}
    	
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!Member.isMemberByLogin(login)) {
            
        	Member member = new Member();
            member.setLogin(login);
            member.setEmail(email);
            member.setPassword(password);
            Ebean.save(member);
           
            session.setAttribute("member", member);
            
            this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
        }else{
        	request.setAttribute("error", "Your login already exists");
        }
        
        this.getServletContext().getRequestDispatcher( "/signup.jsp" ).forward( request, response );
    }
}
