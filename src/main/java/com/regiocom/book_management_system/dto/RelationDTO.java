package com.regiocom.book_management_system.dto;

import lombok.Data;

@Data
public class RelationDTO {
    private Long bookId;
    private Long authorId;
    private Long publisherId;
    private Long seriesId;
}
