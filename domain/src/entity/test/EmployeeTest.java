package entity.test;

import entity.Employee;
import entity.Person;
import enums.EmployeeRoles;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testEmployeeInitialization() {
        String name = "John Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        BigDecimal salary = new BigDecimal("50000");
        EmployeeRoles role = EmployeeRoles.GERENTE;

        Person person = new Person(name, birthday);
        Employee employee = new Employee(person, salary, role);

        assertEquals(name, employee.getName());
        assertEquals(birthday, employee.getBirthday());
        assertEquals(salary, employee.getSalary());
        assertEquals(role.getDescription(), employee.getRole());
    }

    @Test
    public void testIncreaseSalary() {
        Person person = new Person("John Doe", LocalDate.of(1990, 1, 1));
        BigDecimal salary = new BigDecimal("50000");
        EmployeeRoles role = EmployeeRoles.GERENTE;
        Employee employee = new Employee(person, salary, role);

        BigDecimal percentageToIncrease = new BigDecimal("10");
        BigDecimal expectedNewSalary = new BigDecimal("55000");

        employee.increaseSalary(percentageToIncrease);

        assertEquals(expectedNewSalary, employee.getSalary());
    }

    @Test
    public void testToString() {
        String name = "John Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        BigDecimal salary = new BigDecimal("50000");
        EmployeeRoles role = EmployeeRoles.GERENTE;

        Person person = new Person(name, birthday);
        Employee employee = new Employee(person, salary, role);

        String expectedString = name + " | " + "01/01/1990" + " | " + salary + " | " + role.getDescription();

        assertEquals(expectedString, employee.toString());
    }
}
