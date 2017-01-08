package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ktwtr.forms.PostForm;
import ktwtr.forms.RegisterForm;
import ktwtr.models.Member;
import ktwtr.models.Post;

/**
 *
 * @author rhidja
 */
@WebServlet( "/posts" )
public class Posts extends HttpServlet{
    public static final String ATT_POST = "post";
    public static final String ATT_FORM = "form";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("memberSession");
        
        if(member != null) {
        	 this.getServletContext().getRequestDispatcher( "/posts.jsp" ).forward( request, response );
        }else{
        	request.getRequestDispatcher("/home").forward(request, response);
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
     	
    	PostForm form = new PostForm();
        Post post = form.createPost( request );
 
        if ( post != null && form.getErrors().isEmpty() ) {
            response.sendRedirect("/home");
            return;
        }
        
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_POST, post );
	
        this.getServletContext().getRequestDispatcher( "/post.jsp" ).forward( request, response );
    }
}