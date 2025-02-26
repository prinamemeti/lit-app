
package org.example.letersi.services;

import org.example.letersi.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BooksService extends AbstractService {

    public void insert(Book book) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO books (id, title, isbn, genre_id, author_id, score) VALUES (?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getGenreId());
            statement.setInt(5, book.getAuthorId());
            statement.setDouble(6, book.getScore());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void insertBulk(List<Book> books) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO books (id, title, isbn, genre_id, author_id, score) VALUES (?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (Book book : books) {
                statement.setInt(1, book.getId());
                statement.setString(2, book.getTitle());
                statement.setString(3, book.getIsbn());
                statement.setInt(4, book.getGenreId());
                statement.setInt(5, book.getAuthorId());
                statement.setDouble(6, book.getScore());
                statement.addBatch();
            }
            statement.executeBatch();
            con.commit();
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void deleteBook(int id) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM books WHERE id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection con = getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        try {
            statement = con.createStatement();
            String sql = "SELECT * FROM books";
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Book book = new Book();
                book.setId(resultset.getInt("id"));
                book.setTitle(resultset.getString("title"));
                book.setIsbn(resultset.getString("isbn"));
                book.setAuthorId(resultset.getInt("author_id"));
                book.setGenreId(resultset.getInt("genre_id"));
                book.setScore(resultset.getDouble("score"));
                books.add(book);
            }
            return books;
        } finally {
            con.close();
            if (statement != null) {
                statement.close();
            }
            if (resultset != null) {
                resultset.close();
            }
        }
    }

    public Book getBookById(int id) throws SQLException {
        Book book = null;
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "SELECT * FROM books WHERE id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            resultset = statement.executeQuery();

            if (resultset.next()) {
                book = new Book();
                book.setId(resultset.getInt("id"));
                book.setTitle(resultset.getString("title"));
                book.setIsbn(resultset.getString("isbn"));
                book.setAuthorId(resultset.getInt("author_id"));
                book.setGenreId(resultset.getInt("genre_id"));
                book.setScore(resultset.getDouble("score"));
            }

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
            if (resultset != null) {
                resultset.close();
            }
        }
        return book;

    }


}
