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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ktwtr.models.Member;


/**
 *
 * @author rhidja
 */
@Path("/members")
public class Members {

    private Boolean status;
    private String message;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Member> getAll(){

        return Ebean.find(Member.class).findList();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Member getMember(@PathParam("id") long id) {

        return Ebean.find(Member.class).where().eq("id", id).findUnique();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes("application/x-www-form-urlencoded")
    public RestResponse<Member> addMember(@FormParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {

        Member member = new Member();
        member.setLogin(login);
        member.setEmail(email);
        member.setPassword(password);
        Ebean.save(member);
        this.status = true;
        this.message = "Membre ajouté avec succès";

        return new RestResponse<Member>(this.status, this.message, member);
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Member> updateMember(@PathParam("id") long id, @FormParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {

    	Member member = Ebean.find(Member.class).where().eq("id", id).findUnique();

        if(member != null){
            member.setEmail(login);
            member.setEmail(email);
            member.setPassword(password);
            Ebean.update(member);
            this.message = "Membre modifié avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Member>(this.status, this.message, member);
    }

    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public RestResponse<Member> deleteMember(@PathParam("id") long id) {

    	Member member = Ebean.find(Member.class).where().eq("id", id).findUnique();

        if(member != null){
            Ebean.delete(member);
            this.message = "Membre suprimé avec succès";
            this.status = true;
        }else{
            this.message = "Aucune correspondance pour cet Id";
            this.status = false;
        }

        return new RestResponse<Member>(this.status, this.message, member);
    }
}
