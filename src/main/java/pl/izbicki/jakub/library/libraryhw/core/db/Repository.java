package pl.izbicki.jakub.library.libraryhw.core.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public abstract class Repository<T> {

    @Autowired
    private Connection<T> connection;

    public SelectQuery selectForObject() {
        return new SelectQuery();
    }

    public class SelectQuery {

        private final List<Predicate<T>> predicates = new ArrayList<>();

        public SelectQuery where(final Predicate<T> predicate) {
            this.predicates.add(predicate);
            return this;
        }

        public List<T> execute() {
            return connection.get().stream()
                    .filter(object -> predicates.stream().allMatch(p -> p.test(object)))
                    .collect(Collectors.toList());
        }
    }
}
