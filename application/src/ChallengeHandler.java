import entity.Employee;
import entity.Person;
import enums.EmployeeRoles;
import service.EmployeeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ChallengeHandler {
    private List<Employee> employees;
    private final BigDecimal MINIMUM_WAGE;

    public ChallengeHandler(){
        this.MINIMUM_WAGE = new BigDecimal("1212.00");
        // 3.1
        initializeEmployees();
    }

    public void execute(){
        // 3.2
        removeEmployeeByName("João");

        // 3.3
        System.out.println("\nFuncionários:");
        printEmployeesInformation(this.employees);

        // 3.4
        EmployeeService.increaseSalary(this.employees, new BigDecimal(10));

        // 3.5
        Map<String, List<Employee>> groupedEmployeesByRole = getGroupEmployeesByRole();

        // 3.6
        System.out.println("\nFuncionários por função:");
        printEmployeeGroups(groupedEmployeesByRole);

        // 3.8
        System.out.println("\nAniversariantes de outubro e dezembro:");
        List<Integer> monthsToFilter = new ArrayList<>(Arrays.asList(10, 12));
        List<Employee> filteredEmployeesByMonthOfBirth = getEmployeesByMonthOfBirth(monthsToFilter);
        printEmployeesInformation(filteredEmployeesByMonthOfBirth);

        // 3.9
        System.out.println("\nFuncionário(a) mais velho:");
        Optional<Employee> oldestEmployee = EmployeeService.getOldestEmployee(this.employees);
        oldestEmployee.ifPresent(ChallengeHandler::printEmployeeBasicInformation);

        // 3.10
        List<Employee> employeesOrderedAlphabetically = getOrderedEmployeesByName();
        System.out.println("\nFuncionários em ordem alfabética:");
        printEmployeesInformation(employeesOrderedAlphabetically);

        // 3.11
        BigDecimal totalEmployeesSalary = EmployeeService.getEmployeesTotalSalary(employees);
        System.out.println("\nSalário total dos funcionários: " + totalEmployeesSalary);

        // 3.12
        System.out.println("\nQuantidade de salários mínimos que cada funcionário ganha:");
        printHowManyMinimumWagesEachEmployeeMakes(this.employees, this.MINIMUM_WAGE);
    }

    public void initializeEmployees(){
        // TODO: Criar um arquivo json com todos os funcionários para funcionar como um banco de dados
        Person mariaPerson = new Person("Maria", LocalDate.of(2000, 10, 18));
        Employee mariaEmployee = new Employee(mariaPerson, new BigDecimal("2009.44"), EmployeeRoles.OPERADOR);

        Person joaoPerson = new Person("João", LocalDate.of(1990, 5, 12));
        Employee joaoEmployee = new Employee(joaoPerson, new BigDecimal("2294.38"), EmployeeRoles.OPERADOR);

        Person caioPerson = new Person("Caio", LocalDate.of(1961, 5, 2));
        Employee caioEmployee = new Employee(caioPerson, new BigDecimal("9836.14"), EmployeeRoles.COORDENADOR);

        Person miguelPerson = new Person("Miguel", LocalDate.of(1988, 10, 14));
        Employee miguelEmployee = new Employee(miguelPerson, new BigDecimal("19119.88"), EmployeeRoles.DIRETOR);

        Person alicePerson = new Person("Alice", LocalDate.of(1995, 1, 5));
        Employee aliceEmployee = new Employee(alicePerson, new BigDecimal("2234.68"), EmployeeRoles.RECEPCIONISTA);

        Person heitorPerson = new Person("Heitor", LocalDate.of(1991, 11, 19));
        Employee heitorEmployee = new Employee(heitorPerson, new BigDecimal("1582.72"), EmployeeRoles.OPERADOR);

        Person arthurPerson = new Person("Arthur", LocalDate.of(1993, 3, 31));
        Employee arthurEmployee = new Employee(arthurPerson, new BigDecimal("4071.84"), EmployeeRoles.CONTADOR);

        Person lauraPerson = new Person("Laura", LocalDate.of(1994, 7, 8));
        Employee lauraEmployee = new Employee(lauraPerson, new BigDecimal("3017.45"), EmployeeRoles.GERENTE);

        Person heloisaPerson = new Person("Heloísa", LocalDate.of(2003, 5, 24));
        Employee heloisaEmployee = new Employee(heloisaPerson, new BigDecimal("1606.85"), EmployeeRoles.ELETRICISTA);

        Person helenaPerson = new Person("Helena", LocalDate.of(1996, 9, 2));
        Employee helenaEmployee = new Employee(helenaPerson, new BigDecimal("2799.93"), EmployeeRoles.GERENTE);

        this.employees = new ArrayList<>(List.of(mariaEmployee, joaoEmployee, caioEmployee, miguelEmployee,
                aliceEmployee, heitorEmployee, arthurEmployee, lauraEmployee, heloisaEmployee, helenaEmployee));
    }

    public void removeEmployeeByName(String employeeName){
        this.employees.removeIf(employee -> employee.getName().equals(employeeName));
    }

    public Map<String, List<Employee>> getGroupEmployeesByRole(){
        return this.employees.stream().collect(Collectors.groupingBy(Employee::getRole));
    }

    public List<Employee> getEmployeesByMonthOfBirth(List<Integer> months){
        return this.employees.stream()
                .filter(e -> months.contains(e.getBirthday().getMonthValue()))
                .toList();
    }

    public List<Employee> getOrderedEmployeesByName() {
        List<Employee> employeesOrderedAlphabetically = new ArrayList<>(this.employees);
        employeesOrderedAlphabetically.sort(Comparator.comparing(Employee::getName));
        return employeesOrderedAlphabetically;
    }

    public BigDecimal calculateHowManyMinimumWagesEmployeeMakes(Employee employee){
        return employee.getSalary().divide(MINIMUM_WAGE, RoundingMode.HALF_UP);
    }

    public static void printEmployeesInformation(List<Employee> employees){
        System.out.println("Nome | Data de nascimento | Salário | Função");
        employees.forEach(System.out::println);
    }

    public static void printEmployeeGroups(Map<String, List<Employee>> groupedEmployeesByRole){
        groupedEmployeesByRole.forEach((role, employeeGroup) -> {
            System.out.println("\nFunção: " + role);
            printEmployeesInformation(employeeGroup);
        });
    }

    public static void printEmployeeBasicInformation(Employee employee){
        System.out.println("Nome | Idade");
        System.out.println(employee.getName() + " " + employee.getAge());
    }

    public void printHowManyMinimumWagesEachEmployeeMakes (List<Employee> employees, BigDecimal minimumWage) {
        System.out.println("Nome | Salários mínimos");
        employees.forEach(employee -> {
            System.out.println(employee.getName() + " " + calculateHowManyMinimumWagesEmployeeMakes(employee));
        });
    }
}
