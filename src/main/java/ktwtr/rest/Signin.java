package ktwtr.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/signin")
public class Signin {
    @Context
    HttpServletRequest request;
    
    @POST
    public String getMsg(@FormParam("login") String login, @FormParam("password") String password) {     
        if(Member.isMember(login, password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            return session.getAttribute("login")+" Is connected";
        }
        else{
            return "Is not connected";
        }
    }
}
