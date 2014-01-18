/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.*;

/**
 *
 * @author ram
 */
@Path("/ctrlposts")
public class CtrlPosts {

    @Context
    HttpServletRequest request;

    @Path("/submitpost")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response submitPost(@FormParam("post") String post) {

        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());

        Post newpost = new Post();
        newpost.setAutor(member);
        newpost.setContent(post);
        Post.setPost(newpost);

        List<Post> posts = Post.all();
        for (int i = 0; i < posts.size(); i++) {
            posts.get(i).setComments(Comment.getCmntsByPost(posts.get(i)));
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();
    }

    @Path("/deletepost")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePost(@FormParam("post-id") long postid) {

        Post post = Post.getPost(postid);
        List<Comment> comments = Comment.getCmntsByPost(post);
        Ebean.delete(comments);
        Ebean.delete(post);
        List<Post> posts = Post.all();
        return Response.ok().entity(posts).build();
    }

    @Path("/allposts")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response allPost() {
        List<Post> posts = Post.all();
        for (int i = 0; i < posts.size(); i++) {
            posts.get(i).setComments(Comment.getCmntsByPost(posts.get(i)));
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();

    }
}
