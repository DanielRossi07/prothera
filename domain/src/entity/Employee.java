package entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Employee extends Person {
    private BigDecimal salary;
    private String role;

    public Employee(String name, LocalDate birthday, BigDecimal salary, String role) {
        super(name, birthday);
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getRole() {
        return role;
    }

    public void increaseSalary(BigDecimal percentageToIncrease) {
        BigDecimal increase = salary.multiply(percentageToIncrease).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        this.salary = this.salary.add(increase);
    }

    public BigDecimal calculateHowManyMinimumWagesEmployeeMakes(BigDecimal minimumWage){
        return salary.divide(minimumWage, RoundingMode.HALF_UP);
    }

    public String toString(){
        return this.getName() + " | " +
        this.getBirthday() + " | " +
        this.getSalary() + " | " +
        this.getRole();
    }
}