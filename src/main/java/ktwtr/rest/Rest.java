/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Person;
import ktwtr.models.User;

/**
 *
 * @author ram
 */
@Path("/users")
public class Rest {
    
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
