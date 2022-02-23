package stream.excercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stream.excercise.domain.City;
import stream.excercise.domain.Country;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class CountrySearchTest {
    List<Country> countries;

    CountrySearch countrySearch = new CountrySearch();

    private static final String NORTH_AMERICA = "North america";
    private static final String EUROPE = "Europe";
    private static final City LONDON = City.builder().name("London").population(100).build();

    @BeforeEach
    public void setup() {
        final Country england = Country.builder()
                .name("England")
                .continent(EUROPE)
                .cities(Arrays.asList(
                        LONDON,
                        City.builder().name("Bristol").population(90).build(),
                        City.builder().name("Birmingham").population(80).build()
                        )
                )
                .build();

        final Country scotland = Country.builder()
                .name("Scotland")
                .continent(EUROPE)
                .cities(Arrays.asList(
                        City.builder().name("Dundee").population(100).build(),
                        City.builder().name("Edinburgh").population(90).build(),
                        City.builder().name("Glasgow").population(80).build()
                        )
                )
                .build();

        final Country usa = Country.builder()
                .name("USA")
                .continent(NORTH_AMERICA)
                .cities(Arrays.asList(
                        City.builder().name("New york").population(100).build(),
                        City.builder().name("Washington").population(90).build(),
                        City.builder().name("San jose").population(80).build()
                        )
                )
                .build();
        countries = Arrays.asList(england, scotland, usa);
    }

    @Test
    void findTheHighestPopulateCityInEachCountry() {
        final List<City> actual = countrySearch.findHighestPossibleCityByCountry(this.countries);

//        assertThat(actual.size()).as("countries").isEqualTo(2);
        assertThat(actual.stream().map(City::getName).collect(Collectors.toList())).as("dundee").containsExactly("London", "Dundee", "New york");
    }

    @Test
    void mostPopulateCityByContinent() {
        final Map<String, City> actual = countrySearch.mostPopulateCityByContinent(this.countries);

        assertThat(actual).as("countries").contains(entry(EUROPE, LONDON));
    }
}