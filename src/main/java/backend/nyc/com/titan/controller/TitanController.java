package backend.nyc.com.titan.controller;

import backend.nyc.com.titan.domain.Book;
import backend.nyc.com.titan.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class TitanController {

    private final BookRepository repository;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        log.info("saveBook() - start: book = {}", book);
        Book savedBook = repository.save(book);
        log.info("saveBook() - end: savedBook = {}", savedBook.getId());
        return savedBook;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> getAllBooks() {
        log.info("getAllBooks() - start");
        Collection<Book> collection = repository.findAll();
        log.info("getAllBooks() - end");
        return collection;
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
        log.info("getBookById() - start: id = {}", id);
        Book receivedBook = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id = Not found"));
        log.info("getBookById() - end: book = {}", receivedBook.getId());
        return receivedBook;
    }

    @GetMapping(value = "/books", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> findBookByName(@RequestParam(value = "name") String name) {
        log.info("findBookByName() - start: name = {}", name);
        Collection<Book> collection = repository.findByName(name);
        log.info("findBookByName() - end: collection = {}", collection);
        return collection;
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookById(@PathVariable Long id) {
        log.info("removeBookById() - start: id = {}", id);
        repository.deleteById(id);
        log.info("removeBookById() - end: id = {}", id);
    }

    @DeleteMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
        log.info("removeAllBooks() - start");
        repository.deleteAll();
        log.info("removeAllBooks() - end");
    }
}