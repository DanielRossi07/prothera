package entity;

import enums.EmployeeRoles;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee extends Person {
    private BigDecimal salary;
    private EmployeeRoles role;

    public Employee(Person person, BigDecimal salary, EmployeeRoles role){
        super(person.getName(), person.getBirthday());
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getRole() {
        return role.getDescription();
    }

    public void increaseSalary(BigDecimal percentageToIncrease) {
        BigDecimal increase = salary.multiply(percentageToIncrease).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        this.salary = this.salary.add(increase);
    }

    public String toString(){
        return this.getName() + " | " +
        this.getBirthday() + " | " +
        this.getSalary() + " | " +
        this.getRole();
    }
}