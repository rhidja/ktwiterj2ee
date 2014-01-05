package ktwtr.rest;

import com.avaje.ebean.Ebean;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import ktwtr.models.Member;
import ktwtr.models.Profile;


/**
 *
 * @author ram
 */
@Path("/signup")
public class Signup {
    @Context
    HttpServletResponse response; 
    ServletContext context;
    @POST
    public String getMsg(@FormParam("login") String login,@FormParam("email") String email, @FormParam("password") String password){
        Member member = new Member();
        member.setLogin(login);
        member.setEmail(email);
        member.setPassword(password);
        Ebean.save(member);
        
        //Profile profile = new Profile();
        //profile.setMember(Member.getMember(login));
        //Ebean.save(profile);
                
        return "Member Signed up";  
    }
}
