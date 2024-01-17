package Services;

import org.example.Employee;
import org.example.Exceptions.WrongFirstOrLastName;
import org.example.Services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {
    @ParameterizedTest
    @MethodSource("params")
    void add(String firstName, String lastName, int departmentId, float salary, Employee expected) throws WrongFirstOrLastName {
        EmployeeService employeeService = new EmployeeService(new HashMap<>());
        Employee actual = employeeService.add(firstName, lastName, departmentId, salary);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("params")
    void remove(String firstName, String lastName, int departmentId, float salary, Employee expected) throws WrongFirstOrLastName {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), departmentId, salary);
        EmployeeService employeeService = new EmployeeService(new HashMap<>());
        employeeService.add(firstName, lastName, departmentId, salary);
        employeeService.remove(firstName,lastName);
        if(employeeService.getEmployees().containsKey(lastName + firstName)){
            Assertions.assertEquals(expected, employeeService.getEmployees().get(employee.toKey()));
        } else {
            Assertions.assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("params")
    void find(String firstName, String lastName, int departmentId, float salary, Employee expected) throws WrongFirstOrLastName {
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), departmentId, salary);
        EmployeeService employeeService = new EmployeeService(new HashMap<>());
        employeeService.add(firstName, lastName, departmentId, salary);
        employeeService.find(firstName, lastName);
        Assertions.assertEquals(expected, employeeService.getEmployees().get(employee.toKey()));
    }

    private Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Вася", "Пупкин",1,100.00f,new Employee("Вася", "Пупкин",1,100.00f))
        );
    }
}