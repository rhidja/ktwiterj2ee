package ktwtr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ktwtr.models.Post;

/**
 *
 * @author rhidja
 */
public class Home extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    	List<Post> posts = Post.all();
        request.setAttribute( "posts", posts );

        this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
    }
}