package com.lewisu.leonard.cs.bookrating;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookRating {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bookName;
    private String authorName;
    private String genre;
    private int rating;

    public BookRating(){
        bookName = "";
        authorName = "";
        genre  = "";
        rating = 0;
    }

    public BookRating(String bookName, String authorName, String genre, int rating) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.genre = genre;
        this.rating = rating;
    }

    public int getId(){return id; }

    public void setId(int id){ this.id = id; }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return  "BookName='" + bookName + "\n" +
                "AuthorName='" + authorName + "\n" +
                "Genre='" + genre + "\n" +
                "rating=" + rating;
    }
}
