package pl.izbicki.jakub.library.libraryhw.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.izbicki.jakub.library.libraryhw.book.dto.BookDto;
import pl.izbicki.jakub.library.libraryhw.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    public ResponseEntity getBookByIsbn(@PathVariable(value = "isbn") final String isbn) {
        final BookDto bookByIsbn = bookService.getBookByIsbn(isbn);

        return new ResponseEntity(bookByIsbn, HttpStatus.OK);
    }

}
