package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Comment;
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
              
        Profile profile = new Profile();
        profile.setMember(Member.getMember("ram"));
        Profile.setProfile(profile);
        
        Post post = new Post();
        post.setContent("Saluuuuuuuuuuuuuuut");
        post.setTitle("Slt");
        post.setAutor(Member.getMember("ram"));
        post.setPostDate(null);
        Ebean.save(post);       
        
        member2.setLogin("said");
        member2.setEmail("said@gmail.com");
        member2.setPassword("123");
        Ebean.save(member2);
        
        Profile profile2 = new Profile();
        profile2.setMember(Member.getMember("said"));
        Profile.setProfile(profile2);
        
        Post post1 = new Post();
        post1.setContent("Saluuuuuuuuuuuuuuut");
        post1.setTitle("Slt");
        post1.setAutor(Member.getMember("ram"));
        post1.setPostDate(null);
        Ebean.save(post1);  

        post = Post.getPost(1);
        member = Member.getMember("ram");
        
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent("My first Comment");
        comment.setCommentDate(null);
        comment.setAutor(member);
        Comment.setComment(comment);
        
        List<Comment> cmnts = Comment.all();
        
      
        List<Member> members = Member.members();
        
        return Response.ok().entity(cmnts).build();
    }
}
