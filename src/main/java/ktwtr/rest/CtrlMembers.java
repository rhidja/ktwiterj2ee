/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;

import java.net.URI;
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
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/members")
public class CtrlMembers {
    
    @Context
    HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Member> members (){
        return Member.members();
    }

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response newMember(@FormParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {
        
        Member member = new Member();
        member.setLogin(login);
        member.setEmail(email);
        member.setPassword(password);
        Ebean.save(member);
                
        return Response.ok().entity("L'utilisateur : " + login + "est ajouté!").build();
    }
    
    @Path("/{login}")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Member get(@PathParam("login") String login) {
    	Member member = Member.getMember(login);
        return member;
    }
    
    @Path("/{login}")
    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteMember(@PathParam("login") String login) {
      
    	Member member = Member.getMember(login);
        Ebean.delete(member);
        return Response.ok().entity("The member '" + login + "' is deleted.").build();
    }
    
    @Path("/{login}")
    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    public Response updateMember(@PathParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {
    	
    	Member member = Member.getMember(login);
        member.setEmail(email);
        member.setPassword(password);
        Ebean.update(member);
        
        return Response.ok().entity("The member is updated.").build();
    }
}
