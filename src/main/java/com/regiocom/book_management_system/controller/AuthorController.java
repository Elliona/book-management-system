package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.AuthorDTO;
import com.regiocom.book_management_system.repository.AuthorRepository;
import com.regiocom.book_management_system.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/search")
    public List<AuthorDTO> getAllAuthors() {

        return authorService.getAllAuthors();
    }

    @GetMapping("/search/{authorId}")
    public AuthorDTO getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/new")
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.saveAuthor(authorDTO);
    }

    @PatchMapping("/edit/{authorID}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long authorID, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(authorID, authorDTO);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/delete/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
