package com.example.library.repos;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookRepos extends CrudRepository <Book, Long> {
   // List<com.example.library.domain.Book>;
}
