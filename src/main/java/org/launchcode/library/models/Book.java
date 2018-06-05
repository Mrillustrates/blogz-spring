package org.launchcode.library.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="book")
public class Book extends AbstractEntity {

    private String title;
    private String description;
    private String author;
    private String isbn;
    private int pubdate;
    private Librarian librarian;

    public Book(){}

    public Book(String isbn, String title, String description, String author, int pubdate, Librarian librarian){
        super();
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.pubdate = pubdate;
        this.librarian = librarian;

       //this.updated();
        librarian.addBook(this);

    }

    @NotNull
    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @NotNull
    @Column(name="description")
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @NotNull
    @Column(name="author")
    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //@ManyToOne
    public Librarian getLibrarian() {
        return librarian;

    }

    @SuppressWarnings("unused")
    private void setLibrarian(Librarian librarian) {
        this.librarian = librarian;

    }

    @NotNull
    @Column(name= "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @NotNull
    @Column(name="pubdate")
    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
        this.pubdate = pubdate;
    }
}
