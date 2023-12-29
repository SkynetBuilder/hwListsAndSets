package pro.sky.java.course2.adaptedcoursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;
import pro.sky.java.course2.adaptedcoursework.employees.EmployeeServiceImpl;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.course2.adaptedcoursework.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTests {
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 372378963, 1),
            new Employee("Petr", "Petrov", 43365451, 1),
            new Employee("Egor", "Sidorov", 11234621, 2),
            new Employee("Nikita", "Ivanov", 372378963, 2),
            new Employee("Oleg", "Petrov", 43365451, 3),
            new Employee("Olga", "Sidorova", 11234621, 3),
            new Employee("Kristina", "Ivanova", 372378963, 4),
            new Employee("Svetlava", "Petrova", 43365451, 4),
            new Employee("Maria", "Sidorova", 11234621, 5),
            new Employee("Karina", "Sidorova", 11234621, 5)
    ));
    EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void addEmployeeTest() {
        Map<String, Employee> expectedMap = new HashMap<>();
        employees.forEach(employee -> {
            expectedMap.put(employee.toString(), employee);
        });
        employees.forEach(employee -> {
            employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        });
        assertEquals(expectedMap, employeeService.getEmployees());
    }

    @Test
    public void addEmployeeIfAlreadyAddedTest() {
        employeeService.addEmployee(
                employees.get(0).getFirstName(),
                employees.get(0).getLastName(),
                employees.get(0).getSalary(),
                employees.get(0).getDepartment());
        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee(
                    employees.get(0).getFirstName(),
                    employees.get(0).getLastName(),
                    employees.get(0).getSalary(),
                    employees.get(0).getDepartment());
        });
    }

    @Test
    public void invalidInputTest() {
        assertThrows(InvalidInputException.class, () -> {
            employeeService.validateInput("1234!wdt", "2344djkg:");
        });
    }

    @Test
    public void addEmployeeIfStorageIsFullTest() {
        assertThrows(EmployeeStorageIsFullException.class, () -> {
            employees.forEach(employee -> {
                employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
            });
            employeeService.addEmployee("a", "b", 123, 2);
        });
    }

    @Test
    public void findEmployeeTest() {
        employees.forEach(employee -> {
            employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        });
        assertEquals(employees.get(0), employeeService.findEmployee(employees.get(0).getFirstName(), employees.get(0).getLastName()));
    }

    @Test
    public void findEmployeeIfNotFoundTest() {
        employees.forEach(employee -> {
            employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        });
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee("a", "b");
        });
    }

    @Test
    public void removeEmployeeTest() {
        employees.forEach(employee -> {
            employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        });
        assertEquals(employees.get(0), employeeService.removeEmployee(employees.get(0).getFirstName(), employees.get(0).getLastName()));
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee(employees.get(0).getFirstName(), employees.get(0).getLastName());
        });
    }
}