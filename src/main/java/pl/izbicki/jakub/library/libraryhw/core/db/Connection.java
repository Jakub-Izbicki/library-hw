package pl.izbicki.jakub.library.libraryhw.core.db;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import pl.izbicki.jakub.library.libraryhw.book.dao.Book;
import pl.izbicki.jakub.library.libraryhw.core.exception.repository.RepositoryConnectionException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("application.properties")
class Connection<T> {

    private static ObjectMapper MAPPER =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final String repositoryUrl;
    private List<T> cache = new ArrayList<>();

    public Connection(@Value("${app.db.url}") final String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    List<T> get() {
        if (!cache.isEmpty()) {
            return cache;
        }

        try {
            final String json = getJson();
            final RepositoryRoot<T> repositoryRoot = mapToObject(json);

            cache = repositoryRoot.getItems();
            return cache;

        } catch (IOException e) {
            throw new RepositoryConnectionException("Problem connecting to repository!", e);
        }
    }

    private RepositoryRoot<T> mapToObject(String json) throws IOException {
        return MAPPER.readValue(json, MAPPER.getTypeFactory()
                .constructParametricType(RepositoryRoot.class, Book.class));
    }

    private String getJson() throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(repositoryUrl));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
