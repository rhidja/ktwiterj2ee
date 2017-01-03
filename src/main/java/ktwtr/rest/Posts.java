/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ktwtr.models.*;

/**
 *
 * @author ram
 */
@Path("/posts")
public class Posts {

    private Boolean status;
    private String message;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Post> getAll() {

        return Ebean.find(Post.class).findList();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Post getPost(@PathParam("id") long id) {

        return Ebean.find(Post.class).where().eq("id", id).findUnique();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Post> addPost(@FormParam("member_id") long member_id, @FormParam("title") String title, @FormParam("content") String content) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        Member member = Ebean.find(Member.class).where().eq("id", member_id).findUnique();
        post.setAutor(member);

        Ebean.save(post);

        this.status = true;
        this.message = "Post ajouté avec succès";

        return new RestResponse<Post>(this.status, this.message, post);
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Post> updatePost(@PathParam("id") long id, @FormParam("title") String title, @FormParam("content") String content) {

        Post post = Ebean.find(Post.class).where().eq("id", id).findUnique();

        if(post != null){
            post.setTitle(title);
            post.setContent(content);
            Ebean.save(post);
            this.message = "Post modifié avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Post>(this.status, this.message, post);
    }

    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Post> deletePost(@PathParam("id") long id) {

        Post post = Ebean.find(Post.class).where().eq("id", id).findUnique();

        if(post != null){
            Ebean.delete(post);
            this.message = "Post suprimé avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Post>(this.status, this.message, post);
    }
}
