package stream.excercise;

import stream.excercise.domain.City;
import stream.excercise.domain.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CountrySearch {
    public List<City> findHighestPossibleCityByCountry(List<Country> countries) {
        return countries
                .stream()
                .map(country -> country
                        .getCities()
                        .stream()
                        .max(Comparator.comparing(City::getPopulation))
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
