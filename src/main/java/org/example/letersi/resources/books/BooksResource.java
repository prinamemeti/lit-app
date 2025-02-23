package org.example.letersi.resources.books;

import com.google.gson.Gson;
import org.example.letersi.domain.Author;
import org.example.letersi.domain.Book;
import org.example.letersi.services.BooksService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/books")
public class BooksResource {

    BooksService booksService = new BooksService();

    @GET
    public Response getAllBooks() throws SQLException {
        return Response.ok().entity(new Gson().toJson(booksService.getAllBooks())).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) throws SQLException {
        try {
            Book book = booksService.getBookById(id);
            if (book != null) {
                return Response.ok().entity(new Gson().toJson(booksService.getBookById(id))).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Book with this id was not found").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/insert")
    public Response insertBook(String payload) throws Exception {
        booksService.insert(new Gson().fromJson(payload, Book.class));
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/bulk")
    @Consumes(MediaType.APPLICATION_JSON) // This tells JAX-RS to consume JSON data
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertBookBulk(String payload) throws Exception {
        booksService.insertBulk(new Gson().fromJson(payload, Book.class));
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/books")
    public Response getBooks() throws SQLException {
        return Response.ok().entity(new Gson().toJson(booksService.getAllBooks())).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBook(@PathParam("id") int id) throws SQLException {
        try{
            booksService.deleteBook(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



}
