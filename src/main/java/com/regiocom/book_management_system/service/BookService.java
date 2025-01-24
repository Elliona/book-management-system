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

    public BookDTO getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::bookToBookDTO)
                .orElseThrow(() -> new RuntimeException("Book not Found"));
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
//        if (bookDTO.getBookTitle() != null) {
//            existingBook.setBookTitle(bookDTO.getBookTitle());
//        }
//        if (bookDTO.getBookRelease() != null) {
//            existingBook.setBookRelease(bookDTO.getBookRelease());
//        }
//        if (bookDTO.getBookEdition() != null) {
//            existingBook.setBookEdition(bookDTO.getBookEdition());
//        }
//        if (bookDTO.getBookIsbn() != null) {
//            existingBook.setBookIsbn(bookDTO.getBookIsbn());
//        }
//        if (bookDTO.getBookAsin() != null) {
//            existingBook.setBookAsin(bookDTO.getBookAsin());
//        }
//        if (bookDTO.getBookSynopsis() != null) {
//            existingBook.setBookSynopsis(bookDTO.getBookSynopsis());
//        }
//        if (bookDTO.getBookPages() != null) {
//            existingBook.setBookPages(bookDTO.getBookPages());
//        }
//        if (bookDTO.getBookEntryInSeries() != null) {
//            existingBook.setBookEntryInSeries(bookDTO.getBookEntryInSeries());
//        }

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.bookToBookDTO(updatedBook);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
