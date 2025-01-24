package com.regiocom.book_management_system.repository;

import com.regiocom.book_management_system.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findByAuthorName(String name);

}
