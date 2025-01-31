package com.regiocom.book_management_system.mapper;

import com.regiocom.book_management_system.dto.BookDTO;
import com.regiocom.book_management_system.model.Author;
import com.regiocom.book_management_system.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    //    book to bookDTO stuff
    @Mapping(source = "publisher.publisherName", target = "bookPublisherName")
    @Mapping(source = "series.seriesName", target = "bookSeriesName")
    @Mapping(source = "authors", target = "bookAuthors")
    BookDTO bookToBookDTO(Book book);

    default Set<String> mapAuthorsToNames(Set<Author> authors) {
        return authors == null ? Collections.emptySet() : authors.stream()
                .map(Author::getAuthorName)
                .collect(Collectors.toSet());
    }

//    bookDTO to book stuff

    //    @Mapping(source = "bookDTO.bookPublisherName", target = "publisherName")
//    @Mapping(source = "bookDTO.bookSeriesName", target = "seriesName")
//    @Mapping(source = "bookDTO.bookAuthors", target = "authors")
    Book bookDTOToBook(BookDTO bookDTO);

    @Mapping(target = "bookId", ignore = true)
    void updateBookFromDTO(BookDTO bookDTO, @MappingTarget Book book);
}
