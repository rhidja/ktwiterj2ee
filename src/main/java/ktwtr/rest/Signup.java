package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Member;
import ktwtr.models.Post;
import ktwtr.models.Profile;


/**
 *
 * @author ram
 */
@Path("/signup")
public class Signup {
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response listUsers(@FormParam("login") String login,@FormParam("email") String email, @FormParam("password") String password){
        Member member = new Member();
        Member member2 = new Member();
        //member.setLogin(login);
        //member.setEmail(email);
        //member.setPassword(password);
        member.setLogin("ram");
        member.setEmail("ram@gmail.com");
        member.setPassword("123");
        Ebean.save(member);
              
//        Profile profile = new Profile();
//        profile.setMember(Member.getMember("ram"));
//        Ebean.save(profile);
        
        member2.setLogin("said");
        member2.setEmail("said@gmail.com");
        member2.setPassword("123");
        Ebean.save(member2);
        
        Post post = new Post();
        post.setContent("Saluuuuuuuuuuuuuuut");
        post.setTitle("Slt");
        post.setAutor(Member.getMember("ram"));
        Ebean.save(post);
        
//        Profile profile2 = new Profile();
//        profile2.setMember(Member.getMember("said"));
//        Ebean.save(profile2);
        
        List<Member> members = Member.members();
        
        return Response.ok().entity(members.size()).build();
    }
}
