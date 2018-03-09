package com.examples.web.book.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.examples.web.book.Book;

import java.util.List;

/**
 * 
 * @author Sandhya
 * Page request and responces of Book Service
 *
 */

public interface BookService {

    Book save(Book book);

    void delete(Book book);

    Book findOne(String id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, PageRequest pageRequest);

    List<Book> findByTitle(String title);

}