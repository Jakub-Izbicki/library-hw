package pl.izbicki.jakub.library.libraryhw.author.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorRatingDto {

    private final String author;
    private final Double averageRating;
}
