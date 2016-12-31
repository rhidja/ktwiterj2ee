/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.*;

/**
 *
 * @author ram
 */
@Path("/posts")
public class Posts {

    @Context
    HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Post> allPosts() {
        
        return  Post.all();
    }
    
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response submitPost(@FormParam("title") String title, @FormParam("content") String content) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        Ebean.save(post);

        return Response.ok().entity("Le post est sauvegardé.").build();
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    public Response submitPost(@PathParam("id") long id, @FormParam("title") String title, @FormParam("content") String content) {

    	Post post = Post.getPost(id);
        post.setTitle(title);
        post.setContent(content);
        Ebean.save(post);

        return Response.ok().entity("Le post est sauvegardé.").build();
    }
    
    @Path("/{id}")
    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    public Response deletePost(@PathParam("id") long id) {
    	
    	Post post = Post.getPost(id);
        Ebean.delete(post);
        return Response.ok().entity("Post supprimé.").build();
    } 
}
