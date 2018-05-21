package pl.izbicki.jakub.library.libraryhw.book.dao.volume;

import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.izbicki.jakub.library.libraryhw.book.dao.volume.VolumeInfo.YEAR_PATTERN;

public class VolumeInfoTest {

    private static final String FULL_DATE = "2018-01-01";
    private static final Long FULL_DATE_TIMESTAMP = LocalDate.parse(FULL_DATE).toEpochDay();
    private static final String YEAR_DATE = "2018";
    private static final Long YEAR_DATE_TIMESTAMP = DateTimeFormat.forPattern(YEAR_PATTERN)
            .parseLocalDate(YEAR_DATE)
            .toDateTimeAtStartOfDay()
            .getMillis();

    private VolumeInfo volumeInfo;

    @Before
    public void setup() {
        volumeInfo = new VolumeInfo();
    }

    @Test
    public void should_get_date_when_full_date_given() {
        // given
        volumeInfo.setPublishedDate(FULL_DATE);

        // when
        final Optional<Long> date = volumeInfo.getPublishedDate();

        // then
        assertThat(date.get()).isEqualTo(FULL_DATE_TIMESTAMP);
    }

    @Test
    public void should_get_date_when_only_year_given() {
        // given
        volumeInfo.setPublishedDate(YEAR_DATE);

        // when
        final Optional<Long> date = volumeInfo.getPublishedDate();

        // then
        assertThat(date.get()).isEqualTo(YEAR_DATE_TIMESTAMP);
    }
}
