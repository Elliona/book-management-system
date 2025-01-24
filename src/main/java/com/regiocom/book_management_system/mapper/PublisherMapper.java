package com.regiocom.book_management_system.mapper;

import com.regiocom.book_management_system.dto.PublisherDTO;
import com.regiocom.book_management_system.model.Book;
import com.regiocom.book_management_system.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface PublisherMapper {

//    publisher to publisherDTO stuff
    PublisherDTO publisherToPublisherDTO(Publisher publisher);

    default List<String> mapBooksToPublisher(List<Book> books) {
        return books == null ? Collections.emptyList() : books.stream()
                .map(Book::getBookTitle)
                .collect(Collectors.toList());
    }

//    publisherDTO to publisher stuff
    Publisher publisherDTOToPublisher(PublisherDTO publisherDTO);
    @Mapping(target = "publisherId", ignore = true)
    void updatePublisherFromDTO(PublisherDTO publisherDTO, @MappingTarget Publisher publisher);

    default List<Book> mapPublisherToBooks(List<String> bookTitles) {
        return bookTitles == null ? Collections.emptyList() : bookTitles.stream()
                .map(titles -> {
                    Book book = new Book();
                    book.setBookTitle(titles);
                    return book;
                })
                .collect(Collectors.toList());
    }
}
