/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/jrsy")
public class Rest {
    	@GET
	public String getMsg() {
            Member member = new Member();
            member.setLogin("ram");
            member.setPassword("123");
            Ebean.save(member);
            
            String msg;
            if(Member.isMember("said", "123")){ msg = "OK";} else {msg="NotOK";}
            return "Got it : "+msg;
	}   
}
