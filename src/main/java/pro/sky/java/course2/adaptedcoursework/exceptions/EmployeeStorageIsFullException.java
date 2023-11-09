package pro.sky.java.course2.adaptedcoursework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.java.course2.adaptedcoursework.employees.Employee;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(Employee employee) {
        super("Cотрудник " + employee + " не может быть добавлен, т.к. превышено количество сотрудников!");
    }
}
