package pl.izbicki.jakub.library.libraryhw.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.izbicki.jakub.library.libraryhw.author.dto.AuthorRatingDto;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;
import pl.izbicki.jakub.library.libraryhw.book.repository.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<AuthorRatingDto> getAuthorsRatings() {
        final List<Book> books = bookRepository.selectForObject()
                .execute();

        final Set<String> authors = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toSet());

        return authors.stream()
                .filter(author -> authorHasRatings(author, books))
                .map(author -> new AuthorRatingDto(author, provideAuthorAverageRating(author, books)))
                .sorted(Comparator.comparing(AuthorRatingDto::getAverageRating).reversed())
                .collect(Collectors.toList());
    }

    private boolean authorHasRatings(final String author, final List<Book> books) {
        return books.stream()
                .anyMatch(book -> book.getAuthors().contains(author)
                        && book.getAverageRating().isPresent());
    }

    private Double provideAuthorAverageRating(final String author, final List<Book> books) {
        return books.stream()
                .filter(book -> book.getAuthors().contains(author))
                .filter(book -> book.getAverageRating().isPresent())
                .mapToDouble(book -> book.getAverageRating().get())
                .average()
                .getAsDouble();
    }
}
