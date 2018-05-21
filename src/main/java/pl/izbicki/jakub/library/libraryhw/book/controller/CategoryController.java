package pl.izbicki.jakub.library.libraryhw.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Categories", description = "Used for getting books by category.")
public class CategoryController {

    private final BookService bookService;

    @Autowired
    public CategoryController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{categoryName}/books")
    @ApiOperation(value = "Gets books by category. Returns empty list if no books found.", response = BookDto[].class)
    public ResponseEntity getBooksByCategory(@PathVariable(value = "categoryName") final String categoryName) {
        final List<BookDto> booksByCategory = bookService.getBooksByCategory(categoryName);

        return new ResponseEntity(booksByCategory, HttpStatus.OK);
    }
}
