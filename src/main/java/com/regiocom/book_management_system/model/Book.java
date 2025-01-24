package com.regiocom.book_management_system.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "BOOK")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId"
)
public class Book {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "BOOK_RELEASE")
    private String bookRelease;

    @Column(name = "BOOK_EDITION")
    private Integer bookEdition;

    @Column(name = "BOOK_ISBN")
    private String bookIsbn;

    @Column(name = "BOOK_ASIN")
    private String bookAsin;

    @Column(name = "BOOK_SYNOPSIS")
    private String bookSynopsis;

    @Column(name = "BOOK_PAGES")
    private Integer bookPages;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher; // Foreign key

    @ManyToOne
    @JoinColumn(name = "SERIES_ID")
    private Series series; // Foreign key

    @Column(name = "BOOK_ENTRY_IN_SERIES")
    private Integer bookEntryInSeries;

    @ManyToMany(mappedBy = "authorBooks", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Author> authors = new HashSet<>();

    public Book(
            String bookTitle,
            String bookRelease,
            Integer bookEdition,
            String bookIsbn,
            String bookAsin,
            String bookSynopsis,
            Integer bookPages,
            Integer bookEntryInSeries
    ) {
        this.bookTitle = bookTitle;
        this.bookRelease = bookRelease;
        this.bookEdition = bookEdition;
        this.bookIsbn = bookIsbn;
        this.bookAsin = bookAsin;
        this.bookSynopsis = bookSynopsis;
        this.bookPages = bookPages;
        this.bookEntryInSeries = bookEntryInSeries;
    }

    public Book() {
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getAuthorBooks().add(this);
    }

    public void remove(Author author) {
        authors.remove(author);
        author.getAuthorBooks().remove(this);
    }
}
