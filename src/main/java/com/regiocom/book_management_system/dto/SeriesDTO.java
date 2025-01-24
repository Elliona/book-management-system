package com.regiocom.book_management_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeriesDTO {
    private Long seriesId;
    private String seriesName;
    private Integer seriesNumberOfEntries;
    private List<String> seriesBooks;

}
