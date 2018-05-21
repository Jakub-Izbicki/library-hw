package pl.izbicki.jakub.library.libraryhw.book.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.izbicki.jakub.library.libraryhw.book.dao.access.AccessInfo;
import pl.izbicki.jakub.library.libraryhw.book.dao.sale.SaleInfo;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.IndustryIdentifier;
import pl.izbicki.jakub.library.libraryhw.book.dao.volume.VolumeInfo;

import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
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

    private String provideIndustryIdentifierOrId() {
        if (isIdentifiersMissing()) {
            return id;
        }

        final Optional<IndustryIdentifier> isbn = volumeInfo.getIndustryIdentifiers()
                .stream()
                .filter(ISBN_IDENTIFIER)
                .findAny();

        return isbn.isPresent() ? isbn.get().getIdentifier() : id;
    }

    private boolean isIdentifiersMissing() {
        return isNull(volumeInfo)
                || isNull(volumeInfo.getIndustryIdentifiers())
                || volumeInfo.getIndustryIdentifiers().isEmpty();
    }
}
