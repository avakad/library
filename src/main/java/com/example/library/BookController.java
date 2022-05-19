package com.example.library;
import com.example.library.config.MapperUtil;
import com.example.library.domain.Book;
import com.example.library.dto.BookDTO;
import com.example.library.dto.BooksDTO;
import com.example.library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<BooksDTO> getBooks() {
        Iterable<Book> booksIter = bookService.findAll();
        List<Book> booksList = new ArrayList<>();
        booksIter.forEach(booksList::add);
        List<BookDTO> books = MapperUtil.convertList(booksList, this::convertToBookDTO);
        return ResponseEntity.ok(new BooksDTO(books));
    }

    private BookDTO convertToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }


    @PostMapping("/newbook")
    public ResponseEntity<BookDTO> addBook(@RequestParam String bookName, @RequestParam String author) {
        Book bookNew = new Book(bookName, author);
        bookService.save(bookNew);
        BookDTO book = modelMapper.map(bookNew, BookDTO.class);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/newbook1")
    public ResponseEntity<BookDTO> addBook1(@RequestBody BookDTO newBook) {
        Book bookNew = modelMapper.map(newBook, Book.class);
        bookService.save(bookNew);
        BookDTO book = modelMapper.map(bookNew, BookDTO.class);
        return ResponseEntity.ok(book);
    }

    @PutMapping()
    public ResponseEntity<BookDTO> updateBook(@RequestParam Long id, @RequestBody BookDTO bookDTO){
        Book book = bookService.findById(id);
        book.setBookName(bookDTO.getBookName());
        book.setAuthor(bookDTO.getAuthor());
        bookService.save(book);
        BookDTO resultBook = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.ok(resultBook);
    }

//    @GetMapping("/books/{id}")
//    public Book getOne(@PathVariable("id") Book book) {
//        return book;
//    }
//
//    @PostMapping
//    public void add(@RequestParam String bookName, @RequestParam String author, Map<String, Object> model) {
//        Book book = new Book(bookName, author);
//        bookObject.save(book);
//        Iterable<Book> books = bookObject.findAll();
//        model.put("books", books);
//    }


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