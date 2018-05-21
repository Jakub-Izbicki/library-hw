package pl.izbicki.jakub.library.libraryhw.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.izbicki.jakub.library.libraryhw.book.dto.BookDto;
import pl.izbicki.jakub.library.libraryhw.book.service.BookService;
import pl.izbicki.jakub.library.libraryhw.core.exception.entity.NotFoundResponse;

@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Books", description = "Used for getting books.")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    @ApiOperation(value = "Gets books by isbn or if null by id.", response = BookDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Book not found.", response = NotFoundResponse.class),
    })
    public ResponseEntity getBookByIsbn(@PathVariable(value = "isbn") final String isbn) {
        final BookDto bookByIsbn = bookService.getBookByIsbn(isbn);

        return new ResponseEntity(bookByIsbn, HttpStatus.OK);
    }
}
