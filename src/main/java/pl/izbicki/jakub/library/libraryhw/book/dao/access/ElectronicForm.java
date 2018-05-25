package pl.izbicki.jakub.library.libraryhw.book.dao.access;

import lombok.Getter;

@Getter
abstract class ElectronicForm {

    private Boolean isAvailable;
    private String acsTokenLink;
}
