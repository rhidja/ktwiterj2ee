/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/about")
public class About {
        @GET
	@Produces("text/html")
        public Response getAbout() {
            return Response.status(Response.Status.OK).entity("about.html").build();     
	}
}
