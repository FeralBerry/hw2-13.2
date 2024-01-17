package Services;

import org.example.Employee;
import org.example.Exceptions.WrongFirstOrLastName;
import org.example.Services.DepartmentService;
import org.example.Services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentServiceTest {
    private final Map<String,Employee> employeeMap = new HashMap<>();
    private final EmployeeService employeeService = new EmployeeService(employeeMap);
    private final DepartmentService departmentService = new DepartmentService(employeeService);
    @Mock
    private DepartmentGenerate departmentGenerate = new DepartmentGenerate();
    @ParameterizedTest
    @MethodSource("params")
    void maxSalary(List<Employee> employees) throws WrongFirstOrLastName {
        double max = 0;
        for (Employee employee : employees) {
            if (max < employee.getSalary()) {
                max = employee.getSalary();
            }
        }
        employeeService.add("Вася", "Пупкин",1,1000.00f);
        employeeService.add("Иван", "Иванов",1,100.00f);
        employeeService.add("Мария", "Ивановна",2,1000.00f);
        employeeService.add("Петр", "Петрович",2,200.00f);
        employeeService.add("Анна", "Олеговна",3,1000.00f);
        employeeService.add("Семен", "Семенович",3,300.00f);
        // Вместо 1 можно использовать Mokito для правильной работы теста
        Assertions.assertEquals(departmentService.maxSalary(1),max);
    }
    @ParameterizedTest
    @MethodSource("params")
    void minSalary(List<Employee> employees) throws WrongFirstOrLastName {
        double min = Double.MAX_VALUE;
        for (Employee employee : employees) {
            if (min > employee.getSalary()) {
                min = employee.getSalary();
            }
        }
        employeeService.add("Вася", "Пупкин",1,1000.00f);
        employeeService.add("Иван", "Иванов",1,100.00f);
        employeeService.add("Мария", "Ивановна",2,1000.00f);
        employeeService.add("Петр", "Петрович",2,200.00f);
        employeeService.add("Анна", "Олеговна",3,1000.00f);
        employeeService.add("Семен", "Семенович",3,300.00f);
        // Вместо 1 можно использовать Mokito для правильной работы теста
        Assertions.assertEquals(departmentService.minSalary(1),min);
    }

    @ParameterizedTest
    @MethodSource("params")
    void sumSalary(List<Employee> employees) throws WrongFirstOrLastName {
        employeeService.add("Вася", "Пупкин",1,1000.00f);
        employeeService.add("Иван", "Иванов",1,100.00f);
        employeeService.add("Мария", "Ивановна",2,1000.00f);
        employeeService.add("Петр", "Петрович",2,200.00f);
        employeeService.add("Анна", "Олеговна",3,1000.00f);
        employeeService.add("Семен", "Семенович",3,300.00f);
        // Вместо 1 можно использовать Mokito для правильной работы теста
        Assertions.assertEquals(departmentService.sumSalary(1),
                employees.stream()
                        .filter(item -> item.getDepartment() == 1)
                        .mapToDouble(Employee::getSalary)
                        .sum());
    }
    @ParameterizedTest
    @MethodSource("params")
    void allForDepartments(List<Employee> employees) throws WrongFirstOrLastName {
        employeeService.add("Вася", "Пупкин",1,1000.00f);
        employeeService.add("Иван", "Иванов",1,100.00f);
        employeeService.add("Мария", "Ивановна",2,1000.00f);
        employeeService.add("Петр", "Петрович",2,200.00f);
        employeeService.add("Анна", "Олеговна",3,1000.00f);
        employeeService.add("Семен", "Семенович",3,300.00f);
        List<Employee> employeeList = new ArrayList<>();
        // Вместо 1 можно использовать Mokito для правильной работы теста
        for (int i = 0; i < departmentService.allForDepartments(1).size(); i++){
            employeeList.add(departmentService.allForDepartments(1).get(i).getValue());
        }
        System.out.println(departmentService.allForDepartments(1));
        Assertions.assertTrue(employees.stream().filter(item -> item.getDepartment() == 1).toList().containsAll(employeeList));
    }

    @ParameterizedTest
    @MethodSource("params")
    void all(List<Employee> employees) throws WrongFirstOrLastName {
        employeeService.add("Вася", "Пупкин",1,1000.00f);
        employeeService.add("Иван", "Иванов",1,100.00f);
        employeeService.add("Мария", "Ивановна",2,1000.00f);
        employeeService.add("Петр", "Петрович",2,200.00f);
        employeeService.add("Анна", "Олеговна",3,1000.00f);
        employeeService.add("Семен", "Семенович",3,300.00f);
        Assertions.assertTrue(employees.containsAll(departmentService.all()));
    }
    private Stream<Arguments> params(){
        return Stream.of(
                Arguments.of(List.of(
                        new Employee("Вася", "Пупкин",1,1000.00f),
                        new Employee("Иван", "Иванов",1,100.00f),
                        new Employee("Мария", "Ивановна",2,1000.00f),
                        new Employee("Петр", "Петрович",2,200.00f),
                        new Employee("Анна", "Олеговна",3,1000.00f),
                        new Employee("Семен", "Семенович",3,300.00f)
                ))
        );
    }
}