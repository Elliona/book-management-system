package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.BookDTO;
import com.regiocom.book_management_system.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {

        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDTO getBookById(@PathVariable Long bookId) {

        return bookService.getBookById(bookId);
    }

    @PostMapping
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

//    @PostMapping
//    public Book addBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
//
//    @PutMapping("/{bookId}")
//    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
//        book.setBookId(bookId);
//        return bookService.updateBook(book);
//    }
//
//    @DeleteMapping("/{bookId}")
//    public void deleteBook(@RequestBody Book book, @PathVariable Long bookId) {
//        bookService.deleteBook(bookId);
//    }
}
