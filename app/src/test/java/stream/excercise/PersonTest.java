package stream.excercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

class PersonTest {
    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection =  asList(sara, eva, viktor);
        final Person actual = StreamProblems1.getOldestPerson(collection);

        Assertions.assertThat(actual).as("person").isEqualTo(eva);
    }

    @Test
    public void getOldestPersonShouldEmptyLlistShouldReturnDummy() {
        final Person actual = StreamProblems1.getOldestPerson(Collections.emptyList());

        Assertions.assertThat(actual).as("person").isEqualTo(new Person("", 0));
    }
}