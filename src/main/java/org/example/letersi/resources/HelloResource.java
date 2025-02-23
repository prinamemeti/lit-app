package org.example.letersi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello!";
    }

    @GET
    @Path("/world")
    @Produces("text/plain")
    public String world() {
        return "World!";
    }


}