package com.regiocom.book_management_system.mapper;

import com.regiocom.book_management_system.dto.AuthorDTO;
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
public interface AuthorMapper {

    //    author to authorDTO stuff
    AuthorDTO authorToAuthorDTO(Author author);

    default Set<String> mapBooksToAuthors(Set<Book> books) {
        return books == null ? Collections.emptySet() : books.stream()
                .map(Book::getBookTitle)
                .collect(Collectors.toSet());
    }

//    authorDTO to author stuff

    Author authorDTOToAuthor(AuthorDTO authorDTO);

    @Mapping(target = "authorId", ignore = true)
    void updateAuthorFromDTO(AuthorDTO authorDTO, @MappingTarget Author author);

    default Set<Book> mapAuthorsToBooks(Set<String> bookTitles) {
        return bookTitles == null ? Collections.emptySet() : bookTitles.stream()
                .map(titles -> {
                    Book book = new Book();
                    book.setBookTitle(titles);
                    return book;
                })
                .collect(Collectors.toSet());
    }

}
