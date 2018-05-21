package pl.izbicki.jakub.library.libraryhw.book.dao.volume;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.format.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class VolumeInfo {

    public static final String YEAR_REGEX = "^\\d{4}$";
    public static final String YEAR_PATTERN = "yyyy";

    private String title;
    private String subtitle;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private List<String> authors;
    private List<String> categories;
    private List<IndustryIdentifier> industryIdentifiers;
    private ReadingModes readingModes;
    private String printType;
    private Double averageRating;
    private String maturityRating;
    private Boolean allowAnonLogging;
    private String contentVersion;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;

    public Optional<Long> getPublishedDate() {
        if (nonNull(publishedDate)) {
            return Optional.of(parseDate());
        }

        return Optional.empty();
    }

    private Long parseDate() {
        if (publishedDate.matches(YEAR_REGEX)) {

            return DateTimeFormat.forPattern(YEAR_PATTERN)
                    .parseLocalDate(publishedDate)
                    .toDateTimeAtStartOfDay()
                    .getMillis();
        }

        return LocalDate.parse(publishedDate).toEpochDay();
    }
}
