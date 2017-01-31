/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;

import java.util.Date;
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
@Path("/comments")
public class Comments {

    private Boolean status;
    private String message;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Comment> getAll() {

    	return Ebean.find(Comment.class).findList();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Comment getComment(@PathParam("id") long id) {

        return Ebean.find(Comment.class).where().eq("id", id).findUnique();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Comment> addCommentt(@FormParam("post_id") long post_id, @FormParam("author_id") long author_id, @FormParam("content") String content) {

        Comment comment = new Comment();
        comment.setContent(content);

        Post post = Ebean.find(Post.class).where().eq("id", post_id).findUnique();
        comment.setPost(post);

        Member author = Ebean.find(Member.class).where().eq("id", author_id).findUnique();
        comment.setAuthor(author);
        
        comment.setCommentDate(new Date());
        
        if( !content.isEmpty() && post != null & author != null){
        	Ebean.save(comment);
        	this.status = true;
        	this.message = "Comment ajouté avec succès";
        }else{
        	this.status = false;
        	this.message = "Comment échoué";        	
        }

        return new RestResponse<Comment>(this.status, this.message, comment);
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Comment> updateComment(@PathParam("id") long id, @FormParam("content") String content) {

    	Comment comment = Ebean.find(Comment.class).where().eq("id", id).findUnique();

        if(comment != null){
        	comment.setContent(content);
            Ebean.update(comment);
            this.message = "Commentaire modifié avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Comment>(this.status, this.message, comment);
    }

    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public  RestResponse<Comment> deleteComment(@PathParam("id") long id) {

    	Comment comment = Ebean.find(Comment.class).where().eq("id", id).findUnique();

        if(comment != null){
            Ebean.delete(comment);
            this.message = "Membre suprimé avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Comment>(this.status, this.message, comment);
    }
}
