package pro.sky.java.course2.adaptedcoursework.employees;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.adaptedcoursework.exceptions.EmployeeStorageIsFullException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final List<Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    private final int MAX_EMPLOYEES_AMOUNT = 10;

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() > MAX_EMPLOYEES_AMOUNT) {
            throw new EmployeeStorageIsFullException(employee);
        }
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException(employee);
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)){
            throw new EmployeeNotFoundException(employee);
        }
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException(employee);
        }
    }
}
