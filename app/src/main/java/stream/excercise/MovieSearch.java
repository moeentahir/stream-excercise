package stream.excercise;

import stream.excercise.domain.Director;
import stream.excercise.domain.Movie;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieSearch {
    public Map<Director, Integer> findNumberOfMoviesByDirector(List<Director> directors) {
        return directors
                .stream()
                .collect(Collectors.toMap(Function.identity(), d -> d.getMovies().size()));
    }
}
