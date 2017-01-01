package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ktwtr.models.Member;

/**
 *
 * @author rhidja
 */
public class Signin extends HttpServlet{

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/signin.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (Member.isMember(login, password)) {

            Member member = Member.getMember(login);
            HttpSession session = request.getSession(true);
            session.setAttribute("member", member);

            this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( request, response );
        }else{
            request.setAttribute("error", "Invalid login or password");
        }

        this.getServletContext().getRequestDispatcher( "/signin.jsp" ).forward( request, response );
    }
}
