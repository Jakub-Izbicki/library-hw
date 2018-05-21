package pl.izbicki.jakub.library.libraryhw.book.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;
import pl.izbicki.jakub.library.libraryhw.book.dto.BookDto;
import pl.izbicki.jakub.library.libraryhw.book.repository.BookRepository;
import pl.izbicki.jakub.library.libraryhw.core.exception.entity.NotFoundException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    public static final String ISBN = "foo";
    @Mock
    private Book book;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    public void should_get_book_by_isbn() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Collections.singletonList(book));

        // when
        final BookDto bookByIsbn = bookService.getBookByIsbn(ISBN);

        // then
        assertThat(bookByIsbn).isNotNull();
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_when_no_book_by_isbn() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Collections.emptyList());

        // when
        final BookDto bookByIsbn = bookService.getBookByIsbn(ISBN);
    }
}
