package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avaje.ebean.Ebean;

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
    public static final String VUE = "/register.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
  
        RegisterForm form = new RegisterForm();
        
        Member member = form.registerMember( request );
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, member );
		
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
