package pro.sky.java.course2.adaptedcoursework.departments;

import pro.sky.java.course2.adaptedcoursework.employees.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Employee maxSalaryOfDepartment(Integer department);

    Employee minSalaryOfDepartment(Integer department);
    Integer salarySum (Integer department);

    List<Employee> allEmployeesOfDepartment(Integer department);

    Map<Integer,List<Employee>> allEmployees();
}
