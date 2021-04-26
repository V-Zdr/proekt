package mk.finki.ukim.proekt.web;

import mk.finki.ukim.proekt.model.*;
import mk.finki.ukim.proekt.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final BusinessUnitService businessUnitService;
    private final CompanyService companyService;
    private final TaskService taskService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, BusinessUnitService businessUnitService, CompanyService companyService, TaskService taskService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.businessUnitService = businessUnitService;
        this.companyService = companyService;
        this.taskService = taskService;
    }

    @GetMapping({"/","/employees"})
    public String getEmployeePage(@RequestParam(required = false) String salarySearch,
                                  @RequestParam (required = false) String searchFilter, Model model){
        List<Employee> employees;
        if (salarySearch==null){
            employees=this.employeeService.findAll();
        }else{
            int salary = Integer.parseInt(salarySearch);
            employees=this.employeeService.filterEmployeesBySalary(salary,searchFilter);
        }
        model.addAttribute("employees",employees);
        List<Department> departments = this.departmentService.findAll();
        model.addAttribute("departments",departments);
        List<BusinessUnit> businessUnits = this.businessUnitService.findAll();
        model.addAttribute("units", businessUnits);
        List<Company> companies = this.companyService.findAll();
        model.addAttribute("companies",companies);
        List<Task> tasks = this.taskService.findAll();
        model.addAttribute("tasks",tasks);
        return "employees.html";
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(Model model){
        List<Department> departments = this.departmentService.findAll();
        model.addAttribute("departments",departments);
        List<BusinessUnit> businessUnits = this.businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        List<Company> companies = this.companyService.findAll();
        model.addAttribute("companies",companies);
        List<Task> tasks = this.taskService.findAll();
        model.addAttribute("tasks",tasks);
        return "form.html";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployeePage(@PathVariable Long id,Model model){
        Employee employee=this.employeeService.findById(id);
        model.addAttribute("employee",employee);
        List<Department> departments = this.departmentService.findAll();
        model.addAttribute("departments",departments);
        List<BusinessUnit> businessUnits = this.businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        List<Company> companies = this.companyService.findAll();
        model.addAttribute("companies",companies);
        List<Task> tasks = this.taskService.findAll();
        model.addAttribute("tasks",tasks);
        return "form.html";
    }

    @PostMapping("/employees")
    public String addEmployee(@RequestParam String name,
                              @RequestParam int salary,
                              @RequestParam Long department,
                              @RequestParam List<Long> tasks){
        Department d=this.departmentService.findById(department);
        this.employeeService.add(name,salary,d,tasks);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}")
    public String editEmployee(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam int salary,
                               @RequestParam Long department,
                               @RequestParam List<Long> tasks){
        Department d=this.departmentService.findById(department);
        this.employeeService.update(id,name,salary,d,tasks);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id){
        this.employeeService.delete(id);
        return"redirect:/employees";
    }

}
