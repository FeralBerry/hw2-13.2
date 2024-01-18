package org.example.Services;

import org.example.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public double maxSalary(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .max(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .get()
                .getValue()
                .getSalary();
    }
    public double minSalary(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .min(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .get()
                .getValue()
                .getSalary();
    }
    public double sumSalary(int departmentId) {
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    public List<Map.Entry<String, Employee>> allForDepartments(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
    public List<Employee> all() {
        List <Employee> employee = new ArrayList<>();
        employeeService.getEmployees().forEach((key, value) ->
                employee.add(value)
        );
        return employee;
    }
}
