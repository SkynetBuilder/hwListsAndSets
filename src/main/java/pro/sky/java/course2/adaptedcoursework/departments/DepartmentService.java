package pro.sky.java.course2.adaptedcoursework.departments;

import pro.sky.java.course2.adaptedcoursework.employees.Employee;

import java.util.List;

public interface DepartmentService {
    Employee maxSalaryOfDepartment(Integer department);

    Employee minSalaryOfDepartment(Integer department);

    List<Employee> allEmployeesOfDepartment(Integer department);

    List<Employee> allEmployeesSorted();
}
