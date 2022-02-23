package stream.excercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stream.excercise.domain.Director;
import stream.excercise.domain.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

class MovieSearchTest {
    public static final Movie TITANIC = Movie.builder().title("Titanic").build();
    public static final Movie AVATAR = Movie.builder().title("Avatar").build();
    public static final Movie MI2 = Movie.builder().title("Mission impossible 2").build();
    public static final Movie FACEOFF = Movie.builder().title("Faceoff").build();
    public static final Movie GOODFELLAS = Movie.builder().title("Goodfellas").build();
    public static final Movie TAXI_DRIVER = Movie.builder().title("Taxi driver").build();

    private final MovieSearch movieSearch = new MovieSearch();

    private Director JOHN_WOO;
    private Director MARTIN_SCORCESSE;
    private Director JAMES_CAMEROON;
    private List<Director> directors = new ArrayList<>();

    @BeforeEach
    void setUp() {
        JOHN_WOO = Director.builder()
                .name("John woo")
                .movies(List.of(FACEOFF, MI2))
                .build();
        JAMES_CAMEROON = Director.builder()
                .name("James cameroon")
                .movies(List.of(TITANIC, AVATAR))
                .build();

        MARTIN_SCORCESSE = Director.builder()
                .name("Martin Scorsesse")
                .movies(List.of(GOODFELLAS, TAXI_DRIVER))
                .build();

        directors = List.of(JOHN_WOO, JAMES_CAMEROON, MARTIN_SCORCESSE);
    }

    @Test
    void findNumberOfMoviesByDirector() {
        final Map<Director, Integer> actual = movieSearch.findNumberOfMoviesByDirector(directors);

        assertThat(actual).contains(
                entry(JOHN_WOO, 2),
                entry(JAMES_CAMEROON, 2),
                entry(MARTIN_SCORCESSE, 2)
        );
    }

    @Test
    void sortMovieByYearAndThenTitle() {
        final List<Movie> actual = List.of(TITANIC, GOODFELLAS, TAXI_DRIVER, AVATAR)
                .stream()
                .sorted(
                        Comparator
                                .comparingInt(Movie::getYear)
                                .reversed()
                                .thenComparing(Movie::getTitle)
                                .reversed()
                                .thenComparing(Movie::getYear)

                )
                .collect(Collectors.toList());

//        assertThat(actual).containsExactly(AVATAR, GOODFELLAS, TAXI_DRIVER, TITANIC);
        assertThat(actual).containsExactly(TITANIC, TAXI_DRIVER, GOODFELLAS, AVATAR);
    }

    @Test
    void sortStrings() {
        final List<String> names = List.of("Moeeeeen", "Maleeha", "Areej");
        final List<String> sorted = names
                .stream()
                .sorted()
                .collect(Collectors.toList());

        assertThat(sorted).containsExactly("Areej", "Maleeha", "Moeeeeen");
    }
}