package pl.izbicki.jakub.library.libraryhw.book.dao.access;

import lombok.Getter;

@Getter
public class AccessInfo {

    private String country;
    private String viewability;
    private Boolean embeddable;
    private Boolean publicDomain;
    private String textToSpeechPermission;
    private Epub epub;
    private Pdf pdf;
    private String webReaderLink;
    private String accessViewStatus;
    private Boolean quoteSharingAllowed;
}
