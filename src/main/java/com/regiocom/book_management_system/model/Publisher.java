package com.regiocom.book_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "PUBLISHER")
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "PUBLISHER_ID")
    private Long publisherId;

    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    @Column(name = "PUBLISHER_ORIGIN_COUNTRY")
    private String publisherOriginCountry;

    @Column(name = "PUBLISHER_ORIGIN_CITY")
    private String publisherOriginCity;

    @Column(name = "PUBLISHER_NUMBER_OF_BOOKS")
    private Integer publisherNumberOfBooks;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)//, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> publisherBooks = new ArrayList<>();

    public Publisher(
            String publisherName,
            String publisherOriginCountry,
            String publisherOriginCity,
            Integer publisherNumberOfBooks

    ) {
        this.publisherName = publisherName;
        this.publisherOriginCountry = publisherOriginCountry;
        this.publisherOriginCity = publisherOriginCity;
        this.publisherNumberOfBooks = publisherNumberOfBooks;
    }

    public Publisher() {

    }

    public void addBook(Book book) {
        this.publisherBooks.add(book);
        book.setPublisher(this);
    }

    public void removeBook(Book book) {
        this.publisherBooks.remove(book);
        book.setPublisher(null);
    }

}
