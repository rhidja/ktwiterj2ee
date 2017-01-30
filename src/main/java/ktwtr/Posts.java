package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ktwtr.forms.PostForm;
import ktwtr.models.Member;
import ktwtr.models.Post;

/**
 *
 * @author rhidja
 */
@WebServlet( name="Post", urlPatterns = "/posts", initParams = @WebInitParam( name = "path", value = "/images/" ) )
@MultipartConfig( location = "c:/fichiers", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class Posts extends HttpServlet{
    public static final String ATT_POST = "post";
    public static final String ATT_FORM = "form";
    
    public static final String PATH = "path";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("memberSession");
        
        if(member != null) {
        	 this.getServletContext().getRequestDispatcher( "/posts.jsp" ).forward( request, response );
        }else{
        	response.sendRedirect( request.getContextPath() + "/home" );
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
     	
    	String path = getServletContext().getRealPath("/") + this.getServletConfig().getInitParameter( PATH );
    	   	
    	PostForm form = new PostForm();
        Post post = form.createPost( request, path );
 
        if ( post != null && form.getErrors().isEmpty() ) {
            response.sendRedirect( request.getContextPath() + "/home");
            return;
        }
        
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_POST, post );
	
        this.getServletContext().getRequestDispatcher( "/posts.jsp" ).forward( request, response );
    }
}