package pl.izbicki.jakub.library.libraryhw.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.izbicki.jakub.library.libraryhw.author.dto.AuthorRatingDto;
import pl.izbicki.jakub.library.libraryhw.author.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/rating")
    public ResponseEntity getAuthorsRatings() {
        final List<AuthorRatingDto> authorsRatings = authorService.getAuthorsRatings();

        return new ResponseEntity(authorsRatings, HttpStatus.OK);
    }
}
