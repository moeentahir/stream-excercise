package stream.excercise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class Director {
    private int id;
    private String name;
    private String imdb;

    @Builder.Default
    private List<Movie> movies = new ArrayList<>();
}
