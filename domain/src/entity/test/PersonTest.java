package entity.test;

import entity.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void testInitialization() {
        String name = "John Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Person person = new Person(name, birthday);

        assertEquals(name, person.getName());
        assertEquals(birthday, person.getBirthday());
    }

    @Test
    public void testGetAge() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Person person = new Person("John Doe", birthday);

        LocalDate currentDate = LocalDate.of(2024, 6, 15);
        int expectedAge = Period.between(birthday, currentDate).getYears();

        assertEquals(expectedAge, person.getAge());
    }
}