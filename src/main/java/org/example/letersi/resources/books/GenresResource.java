package org.example.letersi.resources.books;
import com.google.gson.Gson;
import org.example.letersi.services.GenreService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/genres")
public class GenresResource {

    GenreService genreService = new GenreService();

    @GET
    public Response getGenres() throws SQLException {
        return Response.ok().entity(new Gson().toJson(genreService.getGenres())).build();
    }
}
