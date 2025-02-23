package org.example.letersi.domain;

import com.google.gson.annotations.SerializedName;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String genre;
    private String author;
    private double score;
    @SerializedName("author_id")
    private int authorId;
    @SerializedName("genre_id")
    private int genreId;

    public Book() {
    }

    public Book(int id, String title, String isbn, String genre, String author, double score) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getScore() {
        return score;
    }
}
