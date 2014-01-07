/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 *
 * @author ram
 */
@Path("/hello")
public class Hello {
    @Context
    HttpServletRequest request;
    @GET
    public String sayHello(){
        HttpSession session = request.getSession(true);
        return session.getAttribute("login")+" Is connected";
    }
}
