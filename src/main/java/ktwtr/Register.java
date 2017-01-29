package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ktwtr.forms.RegisterForm;
import ktwtr.models.Member;

/**
 *
 * @author rhidja
 */
@WebServlet( "/register" )
public class Register extends HttpServlet{
    public static final String ATT_USER = "member";
    public static final String ATT_FORM = "form";
    public static final String ATT_MEMBER_SESSION = "memberSession";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/register.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
  
    	HttpSession session = request.getSession();
    	
    	RegisterForm form = new RegisterForm();
        Member member = form.registerMember( request );
 
        if ( member != null && form.getErrors().isEmpty() ) {
            session.setAttribute( ATT_MEMBER_SESSION, member );
            response.sendRedirect( request.getContextPath() + "/home");
        }else{
            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, member );
            session.setAttribute( ATT_MEMBER_SESSION, null );
    	
            this.getServletContext().getRequestDispatcher( "/register.jsp" ).forward( request, response );
        }
    }
}
