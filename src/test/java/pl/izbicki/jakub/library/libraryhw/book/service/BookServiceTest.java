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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private static final String ISBN = "isbn";
    public static final String CATEGORY = "category";

    @Mock
    private Book book1;
    @Mock
    private Book book2;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    public void should_get_book_by_isbn() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Collections.singletonList(book1));

        // when
        final BookDto bookByIsbn = bookService.getBookByIsbn(ISBN);

        // then
        assertThat(bookByIsbn).isNotNull();
        assertThat(bookByIsbn).isEqualToComparingFieldByField(BookDto.from(book1));
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_when_no_book_by_isbn() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Collections.emptyList());

        // when
        bookService.getBookByIsbn(ISBN);

        // then
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void should_get_books_by_category() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Arrays.asList(book1, book2));

        // when
        final List<BookDto> booksByCategory = bookService.getBooksByCategory(CATEGORY);

        // then
        assertThat(booksByCategory).hasSize(2);
    }

    @Test
    public void should_get_no_books_when_nothing_in_repository() {
        // given
        when(bookRepository.selectForObject().where(any()).execute()).thenReturn(Collections.emptyList());

        // when
        final List<BookDto> booksByCategory = bookService.getBooksByCategory(CATEGORY);

        // then
        assertThat(booksByCategory).isEmpty();
    }
}
