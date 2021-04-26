package mk.finki.ukim.proekt.web;

import mk.finki.ukim.proekt.model.BusinessUnit;
import mk.finki.ukim.proekt.model.Department;
import mk.finki.ukim.proekt.service.BusinessUnitService;
import mk.finki.ukim.proekt.service.CompanyService;
import mk.finki.ukim.proekt.service.DepartmentService;
import mk.finki.ukim.proekt.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DepartmentController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final BusinessUnitService businessUnitService;
    private final CompanyService companyService;

    public DepartmentController(EmployeeService employeeService, DepartmentService departmentService, BusinessUnitService businessUnitService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.businessUnitService = businessUnitService;
        this.companyService = companyService;
    }

    @GetMapping("/departments")
    public String getDepartmentsPage(Model model){
        List<Department> departments=this.departmentService.findAll();
        model.addAttribute("departments",departments);
        return "departments.html";
    }

    @GetMapping("/departments/add")
    public String addDepartmentPage(Model model){
        List<BusinessUnit> businessUnits =this.businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        return "department-form";
    }

    @GetMapping("/departments/{id}/edit")
    public String editDepartmentPage(@PathVariable Long id,Model model){
        Department department=this.departmentService.findById(id);
        model.addAttribute("department",department);
        List<BusinessUnit> businessUnits =this.businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        return "department-form";
    }

    @PostMapping("/departments")
    public String addDepartment(@RequestParam String name,
                                 @RequestParam Long businessUnit){
        this.departmentService.add(name,businessUnit);
        return "redirect:/departments";
    }

    @PostMapping("/departments/{id}")
    public String addCompany(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam Long businessUnit){
        this.departmentService.update(id,name,businessUnit);
        return "redirect:/departments";
    }

    @PostMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id){
        this.departmentService.delete(id);
        return"redirect:/departments";
    }
}
