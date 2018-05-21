package pl.izbicki.jakub.library.libraryhw.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.izbicki.jakub.library.libraryhw.core.exception.entity.NotFoundException;
import pl.izbicki.jakub.library.libraryhw.core.exception.entity.NotFoundResponse;
import pl.izbicki.jakub.library.libraryhw.core.exception.repository.RepositoryConnectionException;
import pl.izbicki.jakub.library.libraryhw.core.exception.repository.RepositoryConnectionResponse;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(HttpServletRequest req, NotFoundException e) {
        final NotFoundResponse response =
                new NotFoundResponse(e.getEntity(), e.getIdentifier(), e.getMessage(), req.getServletPath());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepositoryConnectionException.class)
    public ResponseEntity handleRepositoryConnectionError(HttpServletRequest req, NotFoundException e) {

        final RepositoryConnectionResponse response =
                new RepositoryConnectionResponse(e.getMessage(), req.getServletPath());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
