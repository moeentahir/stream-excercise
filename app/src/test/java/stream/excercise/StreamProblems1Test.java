package stream.excercise;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static stream.excercise.StreamProblems1.getEvenAndOdd;
import static stream.excercise.StreamProblems1.getLongestString;
import static stream.excercise.StreamProblems1.getTotalNumberOfLettersOfNamesLongerThanFive;
import static stream.excercise.StreamProblems1.getUnder18Kids;
import static stream.excercise.StreamProblems1.groupByNationality;
import static stream.excercise.StreamProblems1.mapToUppercase;
import static stream.excercise.StreamProblems1.namesToString;
import static stream.excercise.StreamProblems1.partitionAdults;
import static stream.excercise.StreamProblems1.sumAllNumbers;
import static stream.excercise.StreamProblems1.transform;

/***
 * following problems are taken from website: https://www.codingame.com/playgrounds/20782/java-guild-meeting-52018/streams---micro-katas
 */
class StreamProblems1Test {
    @Test
    public void test() {
        System.out.println("Testing if [william, jones, aaron, seppe, frank, gilliam] returns 14");
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam"), 14);

        System.out.println("Testing if [aaron] returns 0");
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("aaron"), 0);
    }

    @Test
    public void test2() {
        System.out.println("Testing if [aaron, frank, william, gilliam] returns [AARON, FRANK, WILLIAM, GILLIAM]");
        assertTrue(mapToUppercase("aaron", "frank", "william", "gilliam")
                .containsAll(asList("AARON", "FRANK", "WILLIAM", "GILLIAM")));

        System.out.println("Testing if [cegeka] returns [CEGEKA]");
        assertTrue(mapToUppercase("cegeka")
                .containsAll(singletonList("CEGEKA")));
    }

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");

        final List<String> transform = transform(collection);

        assertThat(expected).hasSameElementsAs(transform);
    }

    @Test
    public void countAllTheItemsFromTheInnerList() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third", "Hello"));

        final long count = collection
                .stream()
                .mapToInt(List::size)
                .sum();

        assertThat(count).as("Count").isEqualTo(6);
    }

    @Test
    public void calculateShouldSumAllNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertThat(sumAllNumbers(numbers)).isEqualTo(1 + 2 + 3 + 4 + 5);
    }

    @Test
    public void getKidNameShouldReturnNamesOfAllKidsUnder18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);

        List<Person> collection = asList(sara, eva, viktor, anna);
        assertThat(getUnder18Kids(collection))
                .contains("Sara", "Anna")
                .doesNotContain("Viktor", "Eva");
    }

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = partitionAdults(collection);
        assertThat(result.get(true)).hasSameElementsAs(asList(viktor, eva));
        assertThat(result.get(false)).hasSameElementsAs(singletonList(sara));
    }

    @Test
    void getTheLongestString() {
        final Optional<String> actual = getLongestString(List.of("Moeen", "ahmad", "abcdef"));

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).as("name").isEqualTo("abcdef");
    }

    @Test
    void getTheLongestString_withEmptyList() {
        final Optional<String> actual = getLongestString(Collections.emptyList());

        assertThat(actual.isEmpty()).as("isEmpty").isTrue();
    }

    @Test
    public void groupByNationalityTest() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = groupByNationality(collection);
        assertThat(result.get("Norwegian")).hasSameElementsAs(asList(sara, eva));
        assertThat(result.get("Serbian")).hasSameElementsAs(asList(viktor));
    }

    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        assertThat(namesToString(collection))
                .isEqualTo("Names: Sara, Viktor, Eva.");
    }

    @Test
    public void getStringShouldOutputEvenUnevenString() {
        assertThat(getEvenAndOdd(asList(3, 44))).isEqualTo("o3,e44");
        assertThat(getEvenAndOdd(singletonList(3))).isEqualTo("o3");
        assertThat(getEvenAndOdd(asList(1, 2, 3, 4, 5, 6, 7, 8))).isEqualTo("o1,e2,o3,e4,o5,e6,o7,e8");
    }
}