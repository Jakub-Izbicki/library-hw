package pl.izbicki.jakub.library.libraryhw.core.db;

import lombok.Getter;

import java.util.List;

@Getter
class RepositoryRoot<T> {

    private List<T> items;
}
