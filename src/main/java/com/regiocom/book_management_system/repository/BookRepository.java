package com.regiocom.book_management_system.repository;

import com.regiocom.book_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookId(Long bookId);
    List<Book> findByBookTitle(String title);
    List<Book> findByBookEdition(Integer edition);
    List<Book> findByPublisher_PublisherName(String publisherName);
    List<Book> findByAuthors_AuthorName(String authorName);
    List<Book> findByPublisher_PublisherNameAndBookEditionAndBookEntryInSeries(String publisherName, Integer edition, Integer entryInSeries);
//    public Book findByIdAndTitle(Long id, String title);
}
