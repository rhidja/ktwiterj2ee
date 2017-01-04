package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ktwtr.forms.LoginForm;
import ktwtr.models.Member;

/**
 *
 * @author rhidja
 */
@WebServlet( "/login" )
public class Login extends HttpServlet{
    public static final String ATT_MEMBER = "member";
    public static final String ATT_FORM = "form";
    public static final String ATT_MEMBER_SESSION = "memberSession";
    public static final String VUE = "/login.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
        LoginForm form = new LoginForm();

        Member member = form.loginMember( request );

        HttpSession session = request.getSession();

        if ( form.getErrors().isEmpty() ) {
            session.setAttribute( ATT_MEMBER_SESSION, member );
        } else {
            session.setAttribute( ATT_MEMBER_SESSION, null );
        }

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_MEMBER, member );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }    
}
