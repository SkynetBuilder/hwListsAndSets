package pro.sky.java.course2.adaptedcoursework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(Employee employee) {
        super("Cотрудник " + employee + " уже добавлен!");
    }
}
