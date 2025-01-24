package com.regiocom.book_management_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class PublisherDTO {

    private Long publisherId;
    private String publisherName;
    private String publisherOriginCountry;
    private String publisherOriginCity;
    private Integer publisherNumberOfBooks;
    private List<String> publisherBooks;

}
