package com.regiocom.book_management_system.repository;

import com.regiocom.book_management_system.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    public Series findBySeriesName(String name);
}
