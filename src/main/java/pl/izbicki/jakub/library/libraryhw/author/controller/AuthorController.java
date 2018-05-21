package pl.izbicki.jakub.library.libraryhw.author.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.izbicki.jakub.library.libraryhw.author.dto.AuthorRatingDto;
import pl.izbicki.jakub.library.libraryhw.author.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Authors", description = "Used for getting authors with their average book ratings.")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/rating")
    @ApiOperation(value = "Gets authors with their average book ratings.", response = AuthorRatingDto.class)
    public ResponseEntity getAuthorsRatings() {
        final List<AuthorRatingDto> authorsRatings = authorService.getAuthorsRatings();

        return new ResponseEntity(authorsRatings, HttpStatus.OK);
    }
}
