package pro.sky.java.course2.adaptedcoursework.employees;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private final int MAX_EMPLOYEES_AMOUNT = 10;

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() > MAX_EMPLOYEES_AMOUNT) {
            throw new EmployeeStorageIsFullException(employee);
        }
        if (employees.containsKey(employee.toString())) {
            throw new EmployeeAlreadyAddedException(employee);
        }
        employees.put(employee.toString(),employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.toString())) {
            throw new EmployeeNotFoundException(employee);
        }
        employees.remove(employee.toString(),employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.toString())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException(employee);
        }
    }
}
