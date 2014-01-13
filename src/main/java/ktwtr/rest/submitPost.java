/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import ktwtr.models.Member;
import ktwtr.models.Post;

import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Comment;

/**
 *
 * @author ram
 */
@Path("/submitpost")
public class submitPost {

    @Context
    HttpServletRequest request;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response lposts(@FormParam("post") String post) {
        Query<Post> find = Ebean.find(Post.class);

        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());

        Post newpost = new Post();
        newpost.setAutor(member);
        newpost.setContent(post);
        newpost.setPostDate(null);
        Ebean.save(newpost);

        Post post2 = new Post();
        post2.setContent("Saluuuuuuuuuuuuuuut");
        post2.setTitle("Slt");
        post2.setAutor(Member.getMember("ram"));
        post2.setPostDate(null);
        Ebean.save(post2);

        Post post1 = new Post();
        post1.setContent("Saluuuuuuuuuuuuuuut");
        post1.setTitle("Slt");
        post1.setAutor(Member.getMember("ram"));
        post1.setPostDate(null);
        Ebean.save(post1);

        post1 = Post.getPost(1);
        member = Member.getMember("ram");

        Comment comment = new Comment();
        comment.setPost(post1);
        comment.setContent("My first Comment");
        comment.setCommentDate(null);
        comment.setAutor(member);
        Comment.setComment(comment);

        List<Comment> cmnts = Comment.all();
        
        

        List<Post> posts = find.findList();
        return Response.ok().entity(posts).build();
    }
}
