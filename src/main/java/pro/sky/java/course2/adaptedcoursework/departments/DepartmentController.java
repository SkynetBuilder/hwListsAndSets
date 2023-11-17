package pro.sky.java.course2.adaptedcoursework.departments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;
import pro.sky.java.course2.adaptedcoursework.employees.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryOfDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.maxSalaryOfDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryOfDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.minSalaryOfDepartment(departmentId);
    }

    @GetMapping("all")
    public List<Employee> allEmployeesOfDepartment(@RequestParam(required = false, value = "departmentId") Integer departmentId) {
        if (departmentId != null) {
            return departmentService.allEmployeesOfDepartment(departmentId);
        } else {
            return departmentService.allEmployeesSorted();
        }
    }
}
