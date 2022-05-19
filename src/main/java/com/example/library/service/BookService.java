package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;

    public Book findById(Long id){
        return bookRepo.findById(id).get();
    }

    public boolean findByIdBool(Long id){
        findById(id);
        return  true;
    }

    public Iterable<Book> findAll(){
        return bookRepo.findAll();
    }

    public boolean findAllBool(){
        findAll();
        return  true;
    }

    public void save(Book book){
        bookRepo.save(book);
    }

    public boolean saveBool(Book book){
        save(book);
        return true;
    }
}
