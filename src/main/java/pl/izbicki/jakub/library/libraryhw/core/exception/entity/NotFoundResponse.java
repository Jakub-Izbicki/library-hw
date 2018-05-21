package pl.izbicki.jakub.library.libraryhw.core.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundResponse {

    private String entity;
    private String identifier;
    private String message;
    private String path;
}
