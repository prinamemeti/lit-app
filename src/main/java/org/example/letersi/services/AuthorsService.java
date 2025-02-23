package org.example.letersi.services;

import org.example.letersi.domain.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorsService extends AbstractService {


    public void insert(Author author) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO authors (id, name, no_of_books) VALUES (?,?,?)";
            statement = con.prepareStatement(sql);
            statement.setInt(1, author.getId());
            statement.setString(2, author.getName());
            statement.setInt(3, author.getNoOfBooks());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            con.close();
        }
    }

    public static List<Author> getAll() throws Exception {

        List<Author> authors = new ArrayList<>();
        Connection con = getConnection();
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = con.createStatement();
            String sql = "SELECT * FROM authors";
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Author author = new Author();
                author.setId(resultset.getInt("id"));
                author.setName(resultset.getString("name"));
                author.setNoOfBooks(resultset.getInt("no_of_books"));
                authors.add(author);
            }
            return authors;
        } finally {
            con.close();
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

    }
    public void deleteAuthor(int id) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;
        try{
            String sql = "DELETE FROM authors WHERE id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con !=null){
                con.close();
            }
        }
    }

    public Author getAuthorById(int id) throws Exception {
        Author author = null;
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try{
            String sql = "SELECT * FROM authors WHERE id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            resultset = statement.executeQuery();

            if(resultset.next()){
                author = new Author();
                author.setId(resultset.getInt("id"));
                author.setName(resultset.getString("name"));
                author.setNoOfBooks(resultset.getInt("no_of_books"));
            }
        } finally {
            if (resultset != null) {
                resultset.close();
            }
            if (con != null) {
                con.close();
            }
            if (statement != null) {
                statement.close();
            }
        } return author;

    }

}
