package org.example.letersi.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/ping")
public class PingResource {

    @GET
    public Response ping() {
        return Response.ok("I am alive its " + new Date()).build();
    }

}
