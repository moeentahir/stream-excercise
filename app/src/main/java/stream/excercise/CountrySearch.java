package stream.excercise;

import stream.excercise.domain.City;
import stream.excercise.domain.Country;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, City> mostPopulateCityByContinent(List<Country> countries) {
        final Map<String, List<Country>> countriesByContinent = countries
                .stream()
                .collect(Collectors.groupingBy(Country::getContinent));

        Map<String, City> result = new HashMap<>();
        countriesByContinent.forEach((key, value) -> {
            final Optional<City> cityWithMaxPopulation = value.stream()
                    .flatMap(c -> c.getCities().stream())
                    .max(Comparator.comparing(City::getPopulation));
            cityWithMaxPopulation.ifPresent(city -> result.put(key, city));

        });
        return result;
    }
}
