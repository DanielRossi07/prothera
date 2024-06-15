import entity.Employee;
import service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ChallengeHandler {
    private List<Employee> employees;
    private final BigDecimal MINIMUM_WAGE;

    public ChallengeHandler(){
        this.employees = new ArrayList<Employee>() {};
        this.MINIMUM_WAGE = new BigDecimal("1212.00");
    }

    public void execute(){
        // 3.1
        initializeEmployees();

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
        Employee maria = new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Employee joao = new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2294.38"), "Operador");
        Employee caio = new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Cordenador");
        Employee miguel = new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor");
        Employee alice = new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        Employee heitor = new Employee("Heitor", LocalDate.of(1991, 11, 19), new BigDecimal("1582.72"), "Operador");
        Employee arthur = new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador");
        Employee laura = new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");
        Employee heloisa = new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");
        Employee helena = new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");
        this.employees = new ArrayList<Employee>(Arrays.asList(maria, joao, caio, miguel, alice, heitor, arthur, laura, heloisa, helena));
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

    public List<Employee> getOrderedEmployeesByName(){
        List<Employee> employeesOrderedAlphabetically = this.employees;
        employeesOrderedAlphabetically.sort(Comparator.comparing(Employee::getName));
        return employeesOrderedAlphabetically;
    }

    public static void printEmployeesInformation(List<Employee> employees){
        System.out.println("Nome | Data de nascimento | Salário | Função");
        employees.forEach(employee -> {
            System.out.println(employee.toString());
        });
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

    public static void printHowManyMinimumWagesEachEmployeeMakes (List<Employee> employees, BigDecimal minimumWage) {
        employees.forEach(employee -> {
            System.out.println(employee.getName() + " ganha " + employee.calculateHowManyMinimumWagesEmployeeMakes(minimumWage) + " salários mínimos");
        });
    }
}
