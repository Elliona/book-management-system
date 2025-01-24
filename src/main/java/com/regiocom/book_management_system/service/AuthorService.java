package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.dto.AuthorDTO;
import com.regiocom.book_management_system.mapper.AuthorMapper;
import com.regiocom.book_management_system.model.Author;
import com.regiocom.book_management_system.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .map(authorMapper::authorToAuthorDTO)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long authorId, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        authorMapper.updateAuthorFromDTO(authorDTO, existingAuthor);
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.authorToAuthorDTO(updatedAuthor);
    }

    public void deleteAuthor(Long authorId) {

        authorRepository.deleteById(authorId);
    }

}
