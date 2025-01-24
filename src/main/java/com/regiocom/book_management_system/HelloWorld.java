package com.regiocom.book_management_system;

import com.regiocom.book_management_system.repository.AuthorRepository;
import com.regiocom.book_management_system.repository.BookRepository;
import com.regiocom.book_management_system.repository.PublisherRepository;
import com.regiocom.book_management_system.repository.SeriesRepository;
import com.regiocom.book_management_system.service.FillTable;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final SeriesRepository seriesRepository;
    private final BookRepository bookRepository;
    private final FillTable fillTable;

    public HelloWorld(BookRepository bookRepository,
                      FillTable fillTable,
                      AuthorRepository authorRepository,
                      PublisherRepository publisherRepository,
                      SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.fillTable = fillTable;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.seriesRepository = seriesRepository;

        System.out.println("Hello World");
    }


    @EventListener
    public void fillTablesIfEmpty(ApplicationReadyEvent event) {
        if (bookRepository.count() <= 3) {
            if (bookRepository.findByBookTitle("The Lord of the Rings") == null) {
                fillTable.insertBookOne();
            }
            if (bookRepository.findByBookTitle("A Dance with Dragons") == null) {
                fillTable.insertBookTwo();
            }
            if (bookRepository.findByBookTitle("Harry Potter and the Order of the Phoenix") == null) {
                fillTable.insertBookThree();
            }
        }
        if (authorRepository.count() <= 3) {
            if (authorRepository.findByAuthorName("J.R.R. Tolkien") == null) {
                fillTable.insertAuthorOne();
            }
            if (authorRepository.findByAuthorName("G.R.R. Martin") == null) {
                fillTable.insertAuthorTwo();
            }
            if (authorRepository.findByAuthorName("J.K. Rowling") == null) {
                fillTable.insertAuthorThree();
            }
        }
        if (publisherRepository.count() <= 3) {
            if (publisherRepository.findByPublisherName("Harper Collins") == null) {
                fillTable.insertPublisherOne();
            }
            if (publisherRepository.findByPublisherName("Bantam Spectra") == null) {
                fillTable.insertPublisherTwo();
            }
            if (publisherRepository.findByPublisherName("Bloomsbury Publishing") == null) {
                fillTable.insertPublisherThree();
            }
        }
        if (seriesRepository.count() <= 3) {
            if (seriesRepository.findBySeriesName("The Lord of the Rings") == null) {
                fillTable.insertSeriesOne();
            }
            if (seriesRepository.findBySeriesName("A Song of Ice and Fire") == null) {
                fillTable.insertSeriesTwo();
            }
            if (seriesRepository.findBySeriesName("Harry Potter") == null) {
                fillTable.insertSeriesThree();
            }
        }


    }
}
