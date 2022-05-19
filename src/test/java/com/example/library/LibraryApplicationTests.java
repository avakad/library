package com.example.library;

import com.example.library.domain.Book;
import com.example.library.repos.BookRepo;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;


@SpringBootTest
class LibraryApplicationTests {

	@MockBean
	private BookService bookService;

	@Autowired
	private BookRepo bookRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void save(){
		Book book = new Book();

		book.setAuthor("author");
		book.setBookName("bookName");

		boolean isBookSaved = bookService.saveBool(book);

		org.junit.Assert.assertTrue(isBookSaved);
		org.junit.Assert.assertNotNull(book.getBookName());
		org.junit.Assert.assertNotNull(book.getAuthor());

		Mockito.verify(bookRepo, Mockito.times(1)).save(book);
		Mockito.verify(bookService, Mockito.times(1)).save(book);

			//	ArgumentMatchers.eq(book.getEmail());
	//	Mockito.verify(bookRepo, Mockito.times(1)).save(book);
	}

}
