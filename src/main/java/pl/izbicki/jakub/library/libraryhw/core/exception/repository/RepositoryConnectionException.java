package pl.izbicki.jakub.library.libraryhw.core.exception.repository;

public class RepositoryConnectionException extends RuntimeException {
    public RepositoryConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
