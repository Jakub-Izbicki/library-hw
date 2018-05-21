package pl.izbicki.jakub.library.libraryhw.core.exception.entity;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "No results found";

    private final String entity;
    private final String identifier;

    public NotFoundException(final String entity, final String identifier) {
        super(MESSAGE);
        this.entity = entity;
        this.identifier = identifier;
    }
}
