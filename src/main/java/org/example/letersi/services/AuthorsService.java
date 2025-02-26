package org.example.letersi.services;

import org.example.letersi.domain.Author;
import org.example.letersi.domain.Book;

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
            String sql =
                    "select a.*, b.* from authors as a left join books as b \n" +
                    "    on a.id = b.author_id where a.id = ?;";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            resultset = statement.executeQuery();

            while(resultset.next()){
                if(author == null){
                    author = new Author();
                    author.setId(resultset.getInt("id"));
                    author.setName(resultset.getString("name"));
                    author.setNoOfBooks(resultset.getInt("no_of_books"));
                    author.setBooks(new ArrayList<>());
                }

                Book book = new Book();
                book.setId(resultset.getInt("id"));
                book.setTitle(resultset.getString("title"));
                book.setIsbn(resultset.getString("isbn"));
                book.setAuthorId(resultset.getInt("author_id"));
                book.setGenreId(resultset.getInt("genre_id"));
                book.setScore(resultset.getDouble("score"));
                author.getBooks().add(book);

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

    public Author getAuthorAndBooks(String authorName) throws Exception {
        Author author = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM authors WHERE name = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, authorName);
            resultset = statement.executeQuery();
            if (resultset.next()) {
                author = new Author();
                author.setId(resultset.getInt("id"));
                author.setName(resultset.getString("name"));
                author.setNoOfBooks(resultset.getInt("no_of_books"));
            }
        } finally {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }

        if (author == null) {
            return null;
        }

        List<Book> books = getBooksByAuthor(author.getId());
        author.setBooks(books);
        return author;
    }

    public List<Book> getBooksByAuthor(int authorId) throws Exception {
        List<Book> books = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM books WHERE author_id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, authorId);  // Set the author_id parameter

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthorId(resultSet.getInt("author_id"));
                book.setGenreId(resultSet.getInt("genre_id"));
                book.setScore(resultSet.getDouble("score"));
                books.add(book);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            con.close();
        }
        return books;
    }


}
