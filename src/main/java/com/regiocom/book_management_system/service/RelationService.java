package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.dto.RelationDTO;
import com.regiocom.book_management_system.model.Author;
import com.regiocom.book_management_system.model.Book;
import com.regiocom.book_management_system.model.Publisher;
import com.regiocom.book_management_system.model.Series;
import com.regiocom.book_management_system.repository.AuthorRepository;
import com.regiocom.book_management_system.repository.BookRepository;
import com.regiocom.book_management_system.repository.PublisherRepository;
import com.regiocom.book_management_system.repository.SeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RelationService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final SeriesRepository seriesRepository;

    public RelationService(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           PublisherRepository publisherRepository,
                           SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.seriesRepository = seriesRepository;
    }

    public RelationDTO manageRelation(RelationDTO relationDTO) {
        Book book = bookRepository.findById(relationDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (relationDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(relationDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            author.addBook(book);
            authorRepository.save(author);
        }

        if (relationDTO.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(relationDTO.getPublisherId())
                    .orElseThrow(() -> new RuntimeException("Publisher not found"));
            publisher.addBook(book);
            publisherRepository.save(publisher);
        }

        if (relationDTO.getSeriesId() != null) {
            Series series = seriesRepository.findById(relationDTO.getSeriesId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.addBook(book);
            seriesRepository.save(series);
        }

        bookRepository.save(book);
        return relationDTO;
    }
//
//    public void addSeriesToBook(Long seriesId, Long bookId) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);
//        if (optionalBook.isPresent() && optionalSeries.isPresent()) {
//            Book book = optionalBook.get();
//            Series series = optionalSeries.get();
//
//            series.addBook(book);
//
//            seriesRepository.save(series);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Series not found");
//        }
//    }
//
//    public void addPublisherToBook(Long publisherId, Long bookId) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
//        if (optionalBook.isPresent() && optionalPublisher.isPresent()) {
//            Book book = optionalBook.get();
//            Publisher publisher = optionalPublisher.get();
//
//            publisher.addBook(book);
//
//            publisherRepository.save(publisher);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Publisher not found");
//        }
//    }

    public RelationDTO deleteRelation(RelationDTO relationDTO) {
        Book book = bookRepository.findById(relationDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (relationDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(relationDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            author.removeBook(book);
            authorRepository.save(author);
        }
        if (relationDTO.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(relationDTO.getPublisherId())
                    .orElseThrow(() -> new RuntimeException("Publisher not found"));
            publisher.removeBook(book);
            publisherRepository.save(publisher);
        }
        if (relationDTO.getSeriesId() != null) {
            Series series = seriesRepository.findById(relationDTO.getSeriesId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.removeBook(book);
            seriesRepository.save(series);
        }
        bookRepository.save(book);
        return relationDTO;
    }

//    public void deleteAuthorFromBook(Long authorId, Long bookId) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
//        if (optionalBook.isPresent() && optionalAuthor.isPresent()) {
//            Book book = optionalBook.get();
//            Author author = optionalAuthor.get();
//
//            author.removeBook(book);
//
//            authorRepository.save(author);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Book not found");
//        }
//    }
//
//    public void deleteSeriesFromBook(Long seriesId, Long bookId) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);
//        if (optionalBook.isPresent() && optionalSeries.isPresent()) {
//            Book book = optionalBook.get();
//            Series series = optionalSeries.get();
//
//            series.removeBook(book);
//
//            seriesRepository.save(series);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Series not found");
//        }
//    }
//
//    public void deletePublisherFromBook(Long publisherId, Long bookId) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
//        if (optionalBook.isPresent() && optionalPublisher.isPresent()) {
//            Book book = optionalBook.get();
//            Publisher publisher = optionalPublisher.get();
//
//            publisher.removeBook(book);
//
//            publisherRepository.save(publisher);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Publisher not found");
//        }
//    }
}
