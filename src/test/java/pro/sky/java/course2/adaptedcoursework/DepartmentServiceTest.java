package pro.sky.java.course2.adaptedcoursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.adaptedcoursework.departments.DepartmentServiceImpl;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;
import pro.sky.java.course2.adaptedcoursework.employees.EmployeeServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeServiceImpl employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;
    Map<String, Employee> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 372378963, 1),
            new Employee("Petr", "Petrov", 43365451, 1),
            new Employee("Nikita", "Ivanov", 11234621, 2),
            new Employee("Egor", "Sidorov", 372378963, 2),
            new Employee("Oleg", "Petrov", 43365451, 3),
            new Employee("Olga", "Sidorova", 11234621, 3),
            new Employee("Ekaterina", "Petrova", 372378963, 4),
            new Employee("Kristina", "Ivanova", 43365451, 4),
            new Employee("Maria", "Sidorova", 11234621, 5),
            new Employee("Karina", "Sidorova", 11234621, 5)
    ));

    @Test
    public void maxSalaryOfDepartmentTest() {
        int department = 1;
        Employee expectedEmployee = employees.get(0);
        employees.forEach(employee -> {
            map.put(employee.toString(), employee);
        });
        when(employeeService.getEmployees()).thenReturn(map);
        assertEquals(expectedEmployee, departmentService.maxSalaryOfDepartment(department));
    }

    @Test
    public void minSalaryOfDepartmentTest() {
        int department = 2;
        Employee expectedEmployee = employees.get(2);
        employees.forEach(employee -> {
            map.put(employee.toString(), employee);
        });
        when(employeeService.getEmployees()).thenReturn(map);
        assertEquals(expectedEmployee, departmentService.minSalaryOfDepartment(department));
    }

    @Test
    public void salarySumTest() {
        int department = 3;
        int expectedSalary = employees.get(4).getSalary() + employees.get(5).getSalary();
        employees.forEach(employee -> {
            map.put(employee.toString(), employee);
        });
        when(employeeService.getEmployees()).thenReturn(map);
        assertEquals(expectedSalary, departmentService.salarySum(department));
    }

    @Test
    public void allEmployeesOfDepartmentTest() {
        int department = 4;
        Set<Employee> expectedSet = new HashSet<>(){{
            add(employees.get(6));
            add(employees.get(7));
        }};
        employees.forEach(employee -> {
            map.put(employee.toString(), employee);
        });
        when(employeeService.getEmployees()).thenReturn(map);
        Set<Employee> actualSet = new HashSet<>(departmentService.allEmployeesOfDepartment(department));
        assertEquals(expectedSet,actualSet);
        //Использовал Set, т.к. List из Map может выйти не в том порядке, главное наличие тех же сотрудников
    }

    @Test
    public void allEmployeesTest() {
        employees.forEach(employee -> {
            map.put(employee.toString(), employee);
        });
        when(employeeService.getEmployees()).thenReturn(map);
        Map<Integer, List<Employee>> expectedMap = new HashMap<>(){{
            put(1, List.of(employees.get(0), employees.get(1)));
            put(2, List.of(employees.get(2), employees.get(3)));
            put(3, List.of(employees.get(4), employees.get(5)));
            put(4, List.of(employees.get(6), employees.get(7)));
            put(5, List.of(employees.get(8), employees.get(9)));
        }};
        assertEquals(expectedMap,departmentService.allEmployees());
    }
}
