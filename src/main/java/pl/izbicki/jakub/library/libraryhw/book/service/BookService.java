package pl.izbicki.jakub.library.libraryhw.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;
import pl.izbicki.jakub.library.libraryhw.book.dto.BookDto;
import pl.izbicki.jakub.library.libraryhw.book.repository.BookRepository;
import pl.izbicki.jakub.library.libraryhw.core.exception.entity.NotFoundException;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto getBookByIsbn(final String isbn) {
        final Optional<Book> book = bookRepository.selectForObject()
                .where(bok -> isbn.equals(bok.getIsbn()))
                .execute()
                .stream()
                .findAny();

        if (!book.isPresent()) {
            throw new NotFoundException("No results found", Book.class.getSimpleName(), isbn);
        }

        return BookDto.from(book.get());
    }
}
