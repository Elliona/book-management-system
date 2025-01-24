package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.model.Author;
import com.regiocom.book_management_system.model.Book;
import com.regiocom.book_management_system.model.Publisher;
import com.regiocom.book_management_system.model.Series;
import com.regiocom.book_management_system.repository.AuthorRepository;
import com.regiocom.book_management_system.repository.BookRepository;
import com.regiocom.book_management_system.repository.PublisherRepository;
import com.regiocom.book_management_system.repository.SeriesRepository;
import org.springframework.stereotype.Service;

@Service
public class FillTable {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;
    SeriesRepository seriesRepository;

    public FillTable(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            PublisherRepository publisherRepository,
            SeriesRepository seriesRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.seriesRepository = seriesRepository;
    }

    public void insertBookOne() {
//      BOOK_TABLE: String bookTitle, String bookRelease, Integer bookEdition, String bookIsbn, ...
//                  String bookAsin, String bookSynopsis, Integer bookPages, Integer bookEntryInSeries
        Book book = new Book("The Lord of the Rings", "1994", 1, "", "", "Story about a Hobbit and a Ring", 1184, 1);
        bookRepository.save(book);
    }

    public void insertBookTwo() {
        Book book = new Book("A Dance with Dragons", "2011", 2, "978-0553801477", "", "The fith entry in the epic fantasy series A Song of Ice and Fire ", 1056, 5);
        bookRepository.save(book);
    }

    public void insertBookThree() {
        Book book = new Book("Harry Potter and the Order of the Phoenix", "2003", 1, "0-7475-5100-6", "", "Longest Novel in the Series", 766, 5);
        bookRepository.save(book);
    }

    public void insertAuthorOne() {
//      AUTHOR_TABLE: String authorName, String authorFullName, Integer authorYearOfBirth, ...
//                    Integer authorYearOfDeath, Integer authorNumberOfBooks
        Author author = new Author("J.R.R. Tolkien", "John Ronald Reuel Tolkin", 1892, 1973, 5);
        authorRepository.save(author);
    }

    public void insertAuthorTwo() {
        Author author = new Author("G.R.R. Martin", "George Raymond Richard Martin", 1948, null, 6);
        authorRepository.save(author);
    }

    public void insertAuthorThree() {
        Author author = new Author("J.K. Rowling", "Joanne Rowling", 1965, null, 11);
        authorRepository.save(author);
    }

    public void insertPublisherOne() {
//        PUBLISHER_TABLE: String publisherName, String publisherOriginCountry, String publisherOriginCity, ...
//                         Integer publisherNumberOfBooks
        Publisher publisher = new Publisher("Harper Collins", "United Kingdoms", "London", 1);
        publisherRepository.save(publisher);
    }

    public void insertPublisherTwo() {
        Publisher publisher = new Publisher("Bantam Spectra", "United States of America", "New York City", 1);
        publisherRepository.save(publisher);
    }

    public void insertPublisherThree() {
        Publisher publisher = new Publisher("Bloomsbury Publishing", "United Kingdoms", "Bloomsbury", 1);
        publisherRepository.save(publisher);
    }

    public void insertSeriesOne() {
//        SERIES_TABLE: String seriesName, Integer numberOfEntries
        Series series = new Series("The Lord of the Rings", 3);
        seriesRepository.save(series);
    }

    public void insertSeriesTwo() {
        Series series = new Series("A Song of Ice and Fire", 7);
        seriesRepository.save(series);
    }

    public void insertSeriesThree() {
        Series series = new Series("Harry Potter", 7);
        seriesRepository.save(series);
    }
}
