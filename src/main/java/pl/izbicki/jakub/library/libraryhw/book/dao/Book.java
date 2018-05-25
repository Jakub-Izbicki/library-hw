package pl.izbicki.jakub.library.libraryhw.book.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.izbicki.jakub.library.libraryhw.book.dao.access.AccessInfo;
import pl.izbicki.jakub.library.libraryhw.book.dao.sale.SaleInfo;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.IndustryIdentifier;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.VolumeInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    public static final String ISBN_13 = "ISBN_13";
    public static final Predicate<IndustryIdentifier> ISBN_IDENTIFIER =
            i -> ISBN_13.equals(i.getType()) && nonNull(i.getIdentifier());

    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
    private AccessInfo accessInfo;

    public String getIsbn() {
        return provideIndustryIdentifierOrId();
    }

    public List<String> getCategories() {
        return Optional.ofNullable(volumeInfo)
                .map(VolumeInfo::getCategories)
                .orElse(Collections.emptyList());
    }

    public Optional<Double> getAverageRating() {
        return Optional.ofNullable(volumeInfo)
                .map(VolumeInfo::getAverageRating);
    }

    public List<String> getAuthors() {
        return Optional.ofNullable(volumeInfo)
                .map(VolumeInfo::getAuthors)
                .orElse(Collections.emptyList());
    }

    private String provideIndustryIdentifierOrId() {

        return Optional.ofNullable(volumeInfo)
                .map(VolumeInfo::getIndustryIdentifiers)
                .orElse(Collections.emptyList())
                .stream()
                .filter(ISBN_IDENTIFIER)
                .findAny()
                .map(IndustryIdentifier::getIdentifier)
                .orElse(id);
    }
}
