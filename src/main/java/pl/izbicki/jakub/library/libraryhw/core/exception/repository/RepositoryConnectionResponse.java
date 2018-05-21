package pl.izbicki.jakub.library.libraryhw.core.exception.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryConnectionResponse {

    private String message;
    private String path;
}
