package com.regiocom.book_management_system.repository;

import com.regiocom.book_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByBookTitle(String title);
//    public Book findByIdAndTitle(Long id, String title);
}
