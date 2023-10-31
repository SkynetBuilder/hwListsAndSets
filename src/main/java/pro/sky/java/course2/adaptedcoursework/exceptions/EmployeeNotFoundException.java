package pro.sky.java.course2.adaptedcoursework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Employee employee) {
        super("Cотрудник " + employee + " не найден!");
    }
}
