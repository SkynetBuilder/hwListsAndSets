package pro.sky.java.course2.adaptedcoursework.departments;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;
import pro.sky.java.course2.adaptedcoursework.employees.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/salary/sum")
    public Integer salarySum(@PathVariable Integer id) {
        return departmentService.salarySum(id);
    }

    @GetMapping("/{id}/salary/max")
    public Integer maxSalaryOfDepartment(@PathVariable Integer id) {
        return departmentService.maxSalaryOfDepartment(id).getSalary();
    }

    @GetMapping("/{id}/salary/min")
    public Integer minSalaryOfDepartment(@PathVariable Integer id) {
        return departmentService.minSalaryOfDepartment(id).getSalary();
    }

    @GetMapping("/{id}/employees")
    public List<Employee> allEmployeesOfDepartment(@PathVariable Integer id) {
        return departmentService.allEmployeesOfDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allEmployees() {
        return departmentService.allEmployees();
    }
}
