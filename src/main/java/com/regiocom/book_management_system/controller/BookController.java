package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.BookDTO;
import com.regiocom.book_management_system.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/books", produces = {"application/json"})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping(value = "/search", produces = {"application/json"})
    public List<BookDTO> getAllBooks() {

        return bookService.getAllBooks();
    }

    @GetMapping(value = "/search/by", produces = {"application/json"})
    public ResponseEntity<?> getBookByAny(
            @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) Integer bookEdition,
            @RequestParam(required = false) String bookPublisherName,
            @RequestParam(required = false) String bookAuthor) {

        try {
            if (bookTitle == null && bookId == null && bookEdition == null && bookPublisherName == null && bookAuthor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide one of either Title, ID, Edition, Publisher Name, or Author");
            }
            List<BookDTO> foundBook = bookService.getBookByAny(bookTitle, bookId, bookEdition, bookPublisherName, bookAuthor);
            return ResponseEntity.status(HttpStatus.OK).body(foundBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
                    })
            }
    )
    @GetMapping("/search/for")
    public ResponseEntity<?> getBookByPubEdiEntry(
            @RequestParam String bookPublisherName,
            @RequestParam Integer bookEdition,
            @RequestParam Integer bookEntryInSeries) {

        try {
            if (bookPublisherName == null || bookEdition == null || bookEntryInSeries == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide all. Publisher Name and Edition and Entry");
            }
            List<BookDTO> foundBook = bookService.getBookByPubEdiEntry(bookPublisherName, bookEdition, bookEntryInSeries);
            return ResponseEntity.status(HttpStatus.OK).body(foundBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @PatchMapping("/edit/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

}
