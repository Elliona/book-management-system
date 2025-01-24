//package com.regiocom.book_management_system.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import static jakarta.persistence.GenerationType.AUTO;
//
//@Entity
//@Table(name = "AUTHOR_BOOK_RELATION")
//@Data
//public class AuthorBookRelation {
//
//    @Id
//    @GeneratedValue(strategy = AUTO)
//    @Column(name = "RELATION_ID")
//    private long relationId;
//
//    @ManyToMany
//    @JoinColumn(name= "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
//    private Author author;
//
//    @ManyToMany
//    @JoinColumn(name= "BOOK_ID", referencedColumnName = "BOOK_ID")
//    private Book book;
//
//
//    public AuthorBookRelation(
//            String authorName
//    ) {
//        this.authorName = authorName;
//    }
//    public AuthorBookRelation() {
//
//    }

//}
