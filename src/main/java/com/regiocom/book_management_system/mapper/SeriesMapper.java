package com.regiocom.book_management_system.mapper;

import com.regiocom.book_management_system.dto.SeriesDTO;
import com.regiocom.book_management_system.model.Book;
import com.regiocom.book_management_system.model.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SeriesMapper {

    //        series to seriesDTO stuff
    SeriesDTO seriesToSeriesDTO(Series series);

    default List<String> mapBooksToSeries(List<Book> books) {
        return books == null ? Collections.emptyList() : books.stream()
                .map(Book::getBookTitle)
                .collect(Collectors.toList());
    }

    //    seriesDTO to series stuff
    Series seriesDTOToSeries(SeriesDTO seriesDTO);

    @Mapping(target = "seriesId", ignore = true)
    void updateSeriesFromDTO(SeriesDTO seriesDTO, @MappingTarget Series series);

    default List<Book> mapSeriesToBooks(List<String> bookTitles) {
        return bookTitles == null ? Collections.emptyList() : bookTitles.stream()
                .map(titles -> {
                    Book book = new Book();
                    book.setBookTitle(titles);
                    return book;
                })
                .collect(Collectors.toList());
    }
}
