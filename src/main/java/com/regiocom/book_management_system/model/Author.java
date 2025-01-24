package com.regiocom.book_management_system.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "authorId"
)
public class Author {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @Column(name = "AUTHOR_FULL_NAME")
    private String authorFullName;

    @Column(name = "AUTHOR_YEAR_OF_BIRTH")
    private Integer authorYearOfBirth;

    @Column(name = "AUTHOR_YEAR_OF_DEATH")
    private Integer authorYearOfDeath;

    @Column(name = "AUTHOR_NUMBER_OF_BOOKS")
    private Integer authorNumberOfBooks;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//, fetch = FetchType.LAZY)
    @JoinTable(
            name = "AUTHOR_BOOK",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
//    @JsonManagedReference
//    @ToString.Exclude
    private Set<Book> authorBooks = new HashSet<>();


    public Author(
            String authorName,
            String authorFullName,
            Integer authorYearOfBirth,
            Integer authorYearOfDeath,
            Integer authorNumberOfBooks
    ) {
        this.authorName = authorName;
        this.authorFullName = authorFullName;
        this.authorYearOfBirth = authorYearOfBirth;
        this.authorYearOfDeath = authorYearOfDeath;
        this.authorNumberOfBooks = authorNumberOfBooks;
    }

    public Author() {

    }

    public void addBook(Book book) {
        this.authorBooks.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        this.authorBooks.remove(book);
        book.getAuthors().remove(this);
    }
}