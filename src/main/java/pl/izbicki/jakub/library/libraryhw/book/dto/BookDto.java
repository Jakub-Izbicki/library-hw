package pl.izbicki.jakub.library.libraryhw.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private List<String> authors;
    private List<String> categories;

    public static BookDto from(final Book book) {
        final BookDto bookDto = new BookDto();
        bookDto.setIsbn(book.getIsbn());

        if (nonNull(book.getVolumeInfo())) {
            bookDto.setTitle(book.getVolumeInfo().getTitle());
            bookDto.setSubtitle(book.getVolumeInfo().getSubtitle());
            bookDto.setPublisher(book.getVolumeInfo().getPublisher());
            bookDto.setDescription(book.getVolumeInfo().getDescription());
            bookDto.setPageCount(book.getVolumeInfo().getPageCount());
            bookDto.setThumbnailUrl(book.getVolumeInfo().getImageLinks().getThumbnail());

            bookDto.setLanguage(book.getVolumeInfo().getLanguage());
            bookDto.setPreviewLink(book.getVolumeInfo().getPreviewLink());
            bookDto.setAverageRating(book.getVolumeInfo().getAverageRating());
            bookDto.setAuthors(book.getVolumeInfo().getAuthors());
            bookDto.setCategories(book.getVolumeInfo().getCategories());

            if (book.getVolumeInfo().getPublishedDate().isPresent()) {
                bookDto.setPublishedDate(book.getVolumeInfo().getPublishedDate().get());
            }
        }

        return bookDto;
    }
}
