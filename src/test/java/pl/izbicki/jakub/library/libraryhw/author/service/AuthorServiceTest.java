package pl.izbicki.jakub.library.libraryhw.author.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.izbicki.jakub.library.libraryhw.author.dto.AuthorRatingDto;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;
import pl.izbicki.jakub.library.libraryhw.book.repository.BookRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class AuthorServiceTest {

    private static final String AUTHOR = "author";

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BookRepository bookRepository;
    @Mock
    private Book book1;
    @Mock
    private Book book2;
    @InjectMocks
    private AuthorService authorService;

    private Double rating1;
    private Double rating2;
    private Double average;

    @Parameterized.Parameters
    public static Collection<Object[]> ratings() {
        return Arrays.asList(new Object[][]{
                {5d, 3d, 4d}, {5d, null, 5d}
        });
    }

    public AuthorServiceTest(Double rating1, Double rating2, Double average) {
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.average = average;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(bookRepository.selectForObject().execute()).thenReturn(Arrays.asList(book1, book2));
        when(book1.getAuthors()).thenReturn(Collections.singletonList(AUTHOR));
        when(book2.getAuthors()).thenReturn(Collections.singletonList(AUTHOR));
    }

    @Test
    public void should_get_average_rating_for_author_when_ratings_present() {
        // given
        when(book1.getAverageRating()).thenReturn(Optional.of(rating1));
        when(book2.getAverageRating()).thenReturn(Optional.ofNullable(rating2));

        // when
        final List<AuthorRatingDto> authorsRatings = authorService.getAuthorsRatings();

        // then
        assertThat(authorsRatings).hasSize(1);
        assertThat(authorsRatings.get(0))
                .extracting(AuthorRatingDto::getAuthor, AuthorRatingDto::getAverageRating)
                .containsExactly(AUTHOR, average);
    }

    @Test
    public void should_get_no_rating_for_author_when_no_ratings_present() {
        // when
        final List<AuthorRatingDto> authorsRatings = authorService.getAuthorsRatings();

        // then
        assertThat(authorsRatings).isEmpty();
    }

}
