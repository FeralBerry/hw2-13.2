package org.example.RestControllers;

import org.example.Employee;
import org.example.Services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentsService;
    public DepartmentController(DepartmentService departmentsService) {
        this.departmentsService = departmentsService;
    }
    @GetMapping("/{id}/salary/max")
    public double maxSalary(@PathVariable int id) {
        return departmentsService.maxSalary(id);
    }
    @GetMapping("/{id}/salary/min")
    public double minSalary(@PathVariable int id) {
        return departmentsService.minSalary(id);
    }
    @GetMapping("/{id}/salary/sum")
    public double sumSalary(@PathVariable int id) {
        return departmentsService.sumSalary(id);
    }
    @GetMapping(value = "/{id}/employees")
    public List<Map.Entry<String, Employee>> all(@PathVariable int id) {
        return departmentsService.allForDepartments(id);
    }
    @GetMapping(value = "/employees")
    public List<Employee> all() {
        return departmentsService.all();
    }
}
