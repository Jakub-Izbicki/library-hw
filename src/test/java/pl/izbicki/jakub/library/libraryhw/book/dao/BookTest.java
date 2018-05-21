package pl.izbicki.jakub.library.libraryhw.book.dao;

import org.junit.Before;
import org.junit.Test;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.IndustryIdentifier;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.VolumeInfo;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.izbicki.jakub.library.libraryhw.book.dao.Book.ISBN_13;

public class BookTest {

    private static final String ISBN = "foo";
    public static final String ID = "foo2";
    private Book book;

    @Before
    public void setup() {
        book = new Book();
        book.setVolumeInfo(new VolumeInfo());
        book.getVolumeInfo().setIndustryIdentifiers(new ArrayList<>());
    }

    @Test
    public void should_get_book_isbn_when_present() {
        // given
        book.getVolumeInfo().getIndustryIdentifiers().add(new IndustryIdentifier(ISBN_13, ISBN));

        // when
        final String isbn = book.getIsbn();

        // then
        assertThat(isbn).isEqualTo(ISBN);
    }

    @Test
    public void should_get_book_id_when_isbn_not_present() {
        // given
        book.setId(ID);

        // when
        final String isbn = book.getIsbn();

        // then
        assertThat(isbn).isEqualTo(ID);
    }

    @Test
    public void should_get_null_id_when_isbn_and_id_not_present() {
        // given
        book.setId(ID);

        // when
        final String isbn = book.getIsbn();

        // then
        assertThat(isbn).isNotNull();
    }
}
