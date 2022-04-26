package com.example.library.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.library.domain.Book;

public interface BookRepos extends CrudRepository <Book, Long> {
}
