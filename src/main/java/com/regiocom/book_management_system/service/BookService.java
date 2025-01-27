package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.dto.BookDTO;
import com.regiocom.book_management_system.mapper.BookMapper;
import com.regiocom.book_management_system.model.Book;
import com.regiocom.book_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getBookByAny(String bookTitle, Long bookId, Integer bookEdition, String bookPublisherName, String bookAuthor) {
        if (bookTitle != null) {
            List<Book> books = bookRepository.findByBookTitle(bookTitle);
            if (books.isEmpty()) {
                throw new RuntimeException("No book found with title: " + bookTitle);
            }
            return books.stream()
                    .map(bookMapper::bookToBookDTO)
                    .collect(Collectors.toList());
        } else if (bookId != null) {
            List<Book> books = bookRepository.findByBookId(bookId);
            if (books.isEmpty()) {
                throw new RuntimeException("No book found with id: " + bookId);
            }
            return books.stream()
                    .map(bookMapper::bookToBookDTO)
                    .collect(Collectors.toList());
        } else if (bookEdition != null) {
            List<Book> books = bookRepository.findByBookEdition(bookEdition);
            if (books.isEmpty()) {
                throw new RuntimeException("No book found with edition: " + bookEdition);
            }
            return books.stream()
                    .map(bookMapper::bookToBookDTO)
                    .collect(Collectors.toList());
        } else if (bookPublisherName != null) {
            List<Book> books = bookRepository.findByPublisher_PublisherName(bookPublisherName);
            if (books.isEmpty()) {
                throw new RuntimeException("No book found with publisher: " + bookPublisherName);
            }
            return books.stream()
                    .map(bookMapper::bookToBookDTO)
                    .collect(Collectors.toList());
        } else if (bookAuthor != null) {
          List<Book> books = bookRepository.findByAuthors_AuthorName(bookAuthor);
          if (books.isEmpty()) {
              throw new RuntimeException("No book found with author: " + bookAuthor);
          }
            return books.stream()
                    .map(bookMapper::bookToBookDTO)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Book not Found");
        }
    }

    public List<BookDTO> getBookByPubEdiEntry(String bookPublisherName, Integer bookEdition, Integer bookEntryInSeries) {
        List <Book> books = bookRepository.findByPublisher_PublisherNameAndBookEditionAndBookEntryInSeries(bookPublisherName, bookEdition, bookEntryInSeries);
        if (books.isEmpty()) {

            throw new RuntimeException("No book was found");
        }
        return books.stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.bookToBookDTO(savedBook);
    }

    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not Found"));

        bookMapper.updateBookFromDTO(bookDTO, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.bookToBookDTO(updatedBook);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
