/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import com.sun.jersey.api.view.Viewable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import ktwtr.models.Member;
import ktwtr.models.Person;
import ktwtr.models.User;
import org.h2.engine.Session;

/**
 *
 * @author ram
 */
@Path("/test")
public class Rest {

    @Context
    HttpServletRequest request;

    @Path("/link")
    @GET
    @Produces({MediaType.TEXT_HTML})
    public Response bldlink() {

        HttpSession session = request.getSession(true);
        String test;
        if(session.isNew()){
            test = "ok";
        }else{
            test ="notOk";
        }
        return Response.ok().entity(test).build();
    }

    @Path("/users")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response listUsers() {

        User user = new User();
        user.setLogin("ram");
        user.setPwd("123");
        Ebean.save(user);

        Person person = new Person();
        person.setNom("ram");
        person.setPrenom("hidja");
        person.setUser(User.getUser("ram"));
        Ebean.save(person);

        Person person1 = new Person();
        person1.setNom("ram1");
        person1.setPrenom("hidja1");
        person1.setUser(User.getUser("ram"));
        Ebean.save(person1);

        User user2 = new User();
        user2.setLogin("said");
        user2.setPwd("123");
        Ebean.save(user2);

        Person person2 = new Person();
        person2.setNom("said");
        person2.setPrenom("rashdi");
        person2.setUser(User.getUser("said"));
        Ebean.save(person2);

        List<User> users = User.getUsers();

        user = User.getUser("ram");


        return Response.ok().entity(user).build();
    }
}
