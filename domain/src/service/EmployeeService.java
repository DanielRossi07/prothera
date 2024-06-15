package service;

import entity.Employee;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    public EmployeeService(){

    }

    public static void increaseSalary(List<Employee> employees, BigDecimal percentageToIncrease) {
        employees.forEach(employee -> {
            employee.increaseSalary(percentageToIncrease);
        });
    }

    public static Optional<Employee> getOldestEmployee(List<Employee> employees) {
        return employees.stream().min(Comparator.comparing(Employee::getBirthday));
    }

    public static BigDecimal getEmployeesTotalSalary(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}