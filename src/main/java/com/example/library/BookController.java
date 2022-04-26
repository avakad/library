package com.example.library;
import com.example.library.domain.Book;
import com.example.library.repos.BookObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookObject bookObject;

    @GetMapping("/books")
    public Iterable<Book> get(Map<String, Object> model) {
        Iterable<Book> books = bookObject.findAll();
        model.put("books", books);
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable("id") Book book) {
        return book;
    }

    @PostMapping
    public void add(@RequestParam String bookName, @RequestParam String author, Map<String, Object> model) {
        Book book = new Book(bookName, author);
        bookObject.save(book);
        Iterable<Book> books = bookObject.findAll();
        model.put("books", books);
    }

    //__________________________________________________________________________

    /*
    private List<Map<String, String>> book = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First book"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second book"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return book;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getBook(id);
    }

    private Map<String, String> getBook(@PathVariable String id) {
        return book.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> book) {
        book.put("id", String.valueOf(counter++));

        this.book.add(book);

        return book;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> book) {
        Map<String, String> bookFromDb = getBook(id);

        bookFromDb.putAll(book);
        bookFromDb.put("id", id);

        return bookFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> book = getBook(id);
        this.book.remove(book);
    }

     */
}

