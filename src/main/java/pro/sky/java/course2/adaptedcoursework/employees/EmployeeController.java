package pro.sky.java.course2.adaptedcoursework.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.addEmployee(firstName, lastName);
        return employee;
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.removeEmployee(firstName, lastName);
        return employee;
    }
    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.findEmployee(firstName, lastName);
        return employee;
    }
    @GetMapping("/printAll")
    public List<Employee> printAll(){
        return employeeService.getEmployees();
    }
}