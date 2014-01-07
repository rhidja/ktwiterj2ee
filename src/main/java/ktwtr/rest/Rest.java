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
            
            Person person = new Person();
            person.setNom("ram");
            person.setPrenom("hidja");
            Ebean.save(person);
            User user = new User();
            user.setLogin("ram");
            user.setPwd("123");
            user.setPerson(person);
            Ebean.save(user);
            
            
            Person person2 = new Person();
            person2.setNom("said");
            person2.setPrenom("rashdi");
            Ebean.save(person2);
            User user2 = new User();
            user2.setLogin("said");
            user2.setPwd("123");
            user2.setPerson(person2);
            Ebean.save(user2);
            
            
            List<User> users = User.getUsers();
            
            return Response.ok().entity(users).build();
	}   
}
