package com.example.library.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.library.domain.Book;

public interface BookObject extends CrudRepository <Book, Long> {

}
