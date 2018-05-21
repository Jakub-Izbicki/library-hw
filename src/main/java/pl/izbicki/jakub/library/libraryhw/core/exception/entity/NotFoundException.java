package pl.izbicki.jakub.library.libraryhw.core.exception.entity;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String entity;
    private final String identifier;

    public NotFoundException(final String message, final String entity, final String identifier) {
        super(message);
        this.entity = entity;
        this.identifier = identifier;
    }
}
