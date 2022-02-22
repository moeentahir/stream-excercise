package stream.excercise;

import stream.excercise.domain.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamProblems1 {
    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Arrays
                .stream(names)
                .filter(n -> n.length() > 5)
                .mapToInt(String::length)
                .sum();
    }

    public static Collection<String> mapToUppercase(String... names) {
        return Arrays
                .stream(names)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> transform(List<List<String>> collection) {
        return collection
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static Person getOldestPerson(List<Person> people) {

        return people
                .stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(new Person("", 0));


//        Person oldestPerson = new Person("", 0);
//        for (Person person : people) {
//            if (person.getAge() > oldestPerson.getAge()) {
//                oldestPerson = person;
//            }
//        }
//        return oldestPerson;
    }

    public static int sumAllNumbers(List<Integer> numbers) {
        return numbers
                .stream()
                .reduce(0, Integer::sum);

//        return numbers
//                .stream()
//                .mapToInt(n -> n)
//                .sum();
//        int total = 0;
//        for (int number : numbers) {
//            total += number;
//        }
//        return total;
    }

    public static Set<String> getUnder18Kids(List<Person> people) {
        return people
                .stream()
                .filter(p -> p.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toSet());

//        Set<String> kids = new HashSet<>();
//        for (Person person : people) {
//            if (person.getAge() < 18) {
//                kids.add(person.getName());
//            }
//        }
//        return kids;
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people
                .stream()
                .collect(Collectors.groupingBy(p -> p.getAge() >= 18));

//        Map<Boolean, List<Person>> map = new HashMap<>();
//        map.put(true, new ArrayList<>());
//        map.put(false, new ArrayList<>());
//        for (Person person : people) {
//            map.get(person.getAge() >= 18).add(person);
//        }
//        return map;
    }

    public static Optional<String> getLongestString(List<String> names) {
        return names
                .stream()
                .max(Comparator.comparing(String::length));

//        return names
//                .stream()
//                .reduce((x, y) -> x.length() > y.length() ? x : y);
    }

    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        return people
                .stream()
                .collect(Collectors.groupingBy(Person::getNationality));
//        Map<String, List<Person>> map = new HashMap<>();
//        for (Person person : people) {
//            if (!map.containsKey(person.getNationality())) {
//                map.put(person.getNationality(), new ArrayList<>());
//            }
//            map.get(person.getNationality()).add(person);
//        }
//        return map;
    }

    public static String namesToString(List<Person> people) {

        return people
                .stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Names: ", "."));

//        String label = "Names: ";
//        StringBuilder sb = new StringBuilder(label);
//        for (Person person : people) {
//            if (sb.length() > label.length()) {
//                sb.append(", ");
//            }
//            sb.append(person.getName());
//        }
//        sb.append(".");
//        return sb.toString();
    }

    public static String getEvenAndOdd(List<Integer> list) {
        return list.stream()
                .map(i -> {
                    if (i % 2 == 0) {
                        return "e" + i;
                    } else {
                        return "o" + i;
                    }
                })
                .collect(Collectors.joining(","));
    }
}
