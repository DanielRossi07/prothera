package service;

import entity.Employee;
import entity.Person;
import enums.EmployeeRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeServiceTest {

    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        employees = Arrays.asList(
                new Employee(new Person("Alice", LocalDate.of(1985, 5, 10)), new BigDecimal("70000"), EmployeeRoles.GERENTE),
                new Employee(new Person("Bob", LocalDate.of(1990, 6, 20)), new BigDecimal("50000"), EmployeeRoles.COORDENADOR),
                new Employee(new Person("Charlie", LocalDate.of(1980, 7, 30)), new BigDecimal("90000"), EmployeeRoles.OPERADOR)
        );
    }

    @Test
    public void testIncreaseSalary() {
        BigDecimal percentageToIncrease = new BigDecimal("10");

        EmployeeService.increaseSalary(employees, percentageToIncrease);

        assertEquals(new BigDecimal("77000.00"), employees.get(0).getSalary());
        assertEquals(new BigDecimal("55000.00"), employees.get(1).getSalary());
        assertEquals(new BigDecimal("99000.00"), employees.get(2).getSalary());
    }

    @Test
    public void testGetOldestEmployee() {
        Optional<Employee> oldestEmployee = EmployeeService.getOldestEmployee(employees);

        assertTrue(oldestEmployee.isPresent());
        assertEquals("Charlie", oldestEmployee.get().getName());
    }

    @Test
    public void testGetEmployeesTotalSalary() {
        BigDecimal totalSalary = EmployeeService.getEmployeesTotalSalary(employees);

        assertEquals(new BigDecimal("210000.00"), totalSalary);
    }
}
