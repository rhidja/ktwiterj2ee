package ktwtr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avaje.ebean.Ebean;

import ktwtr.models.Post;

/**
 *
 * @author rhidja
 */
@WebServlet( "/home" )
public class Home extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    	List<Post> posts = Ebean.find(Post.class).findList();
        request.setAttribute( "posts", posts );

        this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
    }
}