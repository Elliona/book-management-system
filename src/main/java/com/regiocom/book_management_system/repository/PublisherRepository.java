package com.regiocom.book_management_system.repository;

import com.regiocom.book_management_system.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    public Publisher findByPublisherName(String name);
}
