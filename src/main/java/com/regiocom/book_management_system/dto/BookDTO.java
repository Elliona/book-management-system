package com.regiocom.book_management_system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private Long bookId;
    private String bookTitle;
    private String bookRelease;
    private Integer bookEdition;
    private String bookIsbn;
    private String bookAsin;
    private String bookSynopsis;
    private Integer bookPages;

    private String bookPublisherName;
    private String bookSeriesName;
    private Integer bookEntryInSeries;
    private Set<String> bookAuthors;
}
