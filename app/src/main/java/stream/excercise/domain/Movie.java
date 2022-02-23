package stream.excercise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class Movie {
    private int id;
    private String title;
    private int year;
    private String imdb;

    @Builder.Default
    private List<Genre> genres= new ArrayList<>();
    @Builder.Default
    private List<Director> directors= new ArrayList<>();
}
