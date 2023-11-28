package pro.sky.java.course2.adaptedcoursework.employees;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.adaptedcoursework.exceptions.InvalidInputException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private final int MAX_EMPLOYEES_AMOUNT = 10;

    @Override
    public Map<String, Employee> getEmployees() {
        return Map.copyOf(employees);
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.size() > MAX_EMPLOYEES_AMOUNT) {
            throw new EmployeeStorageIsFullException(employee);
        }
        if (employees.containsKey(employee.toString())) {
            throw new InvalidInputException();
        }
        employees.put(employee.toString(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (StringUtils.isAlpha(employee.toString())) {
            throw new IllegalArgumentException();
        }
        if (!employees.containsKey(employee.toString())) {
            throw new EmployeeNotFoundException(employee);
        }
        employees.remove(employee.toString(), employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.toString())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException(employee);
        }
    }

    private void validateInput(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}