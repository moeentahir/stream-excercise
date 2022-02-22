package stream.excercise.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;
}
