package ktwtr;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ktwtr.models.Comment;
import ktwtr.models.Likes;
import ktwtr.models.Member;
import ktwtr.models.Post;

/**
 *
 * @author rhidja
 */
public class Members extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    	List<Member> members = Member.members();
    	String message = "";
    	
        for (int i = 0; i < members.size(); i++) {
        	System.out.println(members.get(i).getLogin());
        	message += members.get(i).getLogin();
        }
        
        request.setAttribute( "message", message );
        this.getServletContext().getRequestDispatcher( "/members.jsp" ).forward( request, response );
    }
}