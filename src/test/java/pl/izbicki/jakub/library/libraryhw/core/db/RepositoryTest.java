package pl.izbicki.jakub.library.libraryhw.core.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    private List<Point> points;
    @Mock
    private Connection<Point> connection;
    @InjectMocks
    private TestRepository testRepository;

    @Before
    public void setup() {
        points = Arrays.asList(new Point(1, 1), new Point(0, 0));
        when(connection.get()).thenReturn(points);
    }

    @Test
    public void should_get_all_rows_when_select_all() {
        // when
        final List<Point> points = testRepository.selectForObject().execute();

        // then
        assertThat(points).containsExactly(points.get(0), points.get(1));
    }

    @Test
    public void should_get_selected_rows_when_select_with_predicates() {
        // when
        final List<Point> points = testRepository.selectForObject()
                .where(point -> point.getX() > 0)
                .execute();

        // then
        assertThat(points).containsExactly(points.get(0));
    }

    static class TestRepository extends Repository<Point> {
    }
}
