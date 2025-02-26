package org.example.letersi.resources.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.example.letersi.domain.Author;
import org.example.letersi.domain.Book;
import org.example.letersi.services.AuthorsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/authors")
public class AuthorResource {

    AuthorsService authorsService = new AuthorsService();

    @POST
    @Path("/insert")
    public Response insertAuthor(String payload) throws Exception {
        authorsService.insert(new Gson().fromJson(payload, Author.class));
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllAuthors() throws Exception {
        return Response.ok().entity(new Gson().toJson(authorsService.getAll())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) throws Exception {
        try {
            authorsService.deleteAuthor(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") int id) throws Exception {
        return Response.ok().entity(new Gson().toJson(authorsService.getAuthorById(id))).build();
    }

    @GET
    @Path("/{authorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorWithBooks(@PathParam("authorName") String authorName) throws Exception {
        Author author = authorsService.getAuthorAndBooks(authorName);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(new Gson().toJson(author)).build();
    }

}
