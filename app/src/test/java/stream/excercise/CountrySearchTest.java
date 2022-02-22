package stream.excercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stream.excercise.domain.City;
import stream.excercise.domain.Country;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class CountrySearchTest {
    List<Country> countries;

    CountrySearch countrySearch;

    @BeforeEach
    public void setup() {
        countrySearch = new CountrySearch();
        final Country england = Country.builder()
                .name("England")
                .cities(Arrays.asList(
                        City.builder().name("London").population(100).build(),
                        City.builder().name("Bristol").population(90).build(),
                        City.builder().name("Birmingham").population(80).build()
                        )
                )
                .build();

        final Country scotland = Country.builder()
                .name("Scotland")
                .cities(Arrays.asList(
                        City.builder().name("Dundee").population(100).build(),
                        City.builder().name("Edinburgh").population(90).build(),
                        City.builder().name("Glasgow").population(80).build()
                        )
                )
                .build();
        countries = Arrays.asList(england, scotland);
    }

    @Test
    void findTheHighestPopulateCityInEachCountry() {

        final List<City> actual = countrySearch.findHighestPossibleCityByCountry(this.countries);

//        assertThat(actual.size()).as("countries").isEqualTo(2);
        assertThat(actual.stream().map(City::getName).collect(Collectors.toList())).as("dundee").containsExactly("London", "Dundee");

    }
}