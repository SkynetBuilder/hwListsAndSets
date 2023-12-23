package pro.sky.java.course2.adaptedcoursework.departments;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;
import pro.sky.java.course2.adaptedcoursework.employees.EmployeeService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryOfDepartment(Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee minSalaryOfDepartment(Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Integer salarySum(Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Employee> allEmployeesOfDepartment(Integer department) {
        return employeeService.getEmployees().values()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList();
    }

    @Override
    public Map<Integer,List<Employee>> allEmployees() {
        return employeeService.getEmployees().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
