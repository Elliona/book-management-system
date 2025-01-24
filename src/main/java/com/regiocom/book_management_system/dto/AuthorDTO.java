package com.regiocom.book_management_system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuthorDTO {

    private Long authorId;
    private String authorName;
    private String authorFullName;
    private Integer authorYearOfBirth;
    private Integer authorYearOfDeath;
    private Integer authorNumberOfBooks;
    private Set<String> authorBooks;

}
