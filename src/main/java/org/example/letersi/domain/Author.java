package org.example.letersi.domain;

public class Author {
    private int id;
    private String name;
    private int noOfBooks;

    public Author() {
    }

    public Author(int id, String name, int noOfBooks) {
        this.name = name;
        this.noOfBooks = noOfBooks;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public int getNoOfBooks() {
        return noOfBooks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNoOfBooks(int noOfBooks) {
        this.noOfBooks = noOfBooks;
    }
}
