package com.regiocom.book_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "SERIES")
@Data
public class Series {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "SERIES_ID")
    private Long seriesId;

    @Column(name = "SERIES_NAME")
    private String seriesName;

    @Column(name = "SERIES_NUMBER_OF_ENTRYS")
    private Integer seriesNumberOfEntries;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)//, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> seriesBooks = new ArrayList<>();

    public Series(
            String seriesName,
            Integer seriesNumberOfEntries
    ) {
        this.seriesName = seriesName;
        this.seriesNumberOfEntries = seriesNumberOfEntries;
    }

    public Series() {

    }

    public void addBook(Book book) {
        this.seriesBooks.add(book);
        book.setSeries(this);
    }

    public void removeBook(Book book) {
        this.seriesBooks.remove(book);
        book.setSeries(null);
    }
}
