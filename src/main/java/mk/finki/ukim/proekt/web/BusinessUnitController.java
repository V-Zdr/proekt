package mk.finki.ukim.proekt.web;

import mk.finki.ukim.proekt.model.Company;
import mk.finki.ukim.proekt.model.BusinessUnit;
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
public class BusinessUnitController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final BusinessUnitService businessUnitService;
    private final CompanyService companyService;

    public BusinessUnitController(EmployeeService employeeService, DepartmentService departmentService, BusinessUnitService businessUnitService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.businessUnitService = businessUnitService;
        this.companyService = companyService;
    }

    @GetMapping("/businessUnits")
    public String getUnitsPage(Model model){
        List<BusinessUnit> businessUnits =this.businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        return "businessUnits.html";
    }

    @GetMapping("/businessUnits/add")
    public String addUnitPage(Model model){
        List<Company> companies =this.companyService.findAll();
        model.addAttribute("companies", companies);
        return "businessUnit-form";
    }

    @GetMapping("/businessUnits/{id}/edit")
    public String editUnitPage(@PathVariable Long id,Model model){
        BusinessUnit businessUnit =this.businessUnitService.findById(id);
        model.addAttribute("businessUnit", businessUnit);
        List<Company> companies =this.companyService.findAll();
        model.addAttribute("companies", companies);
        return "businessUnit-form";
    }

    @PostMapping("/businessUnits")
    public String addUnit(@RequestParam String name,
                          @RequestParam Long company){
        this.businessUnitService.add(name,company);
        return "redirect:/businessUnits";
    }

    @PostMapping("/businessUnits/{id}")
    public String editUnit(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam Long company){
        this.businessUnitService.update(id,name,company);
        return "redirect:/businessUnits";
    }

    @PostMapping("/businessUnits/{id}/delete")
    public String deleteUnit(@PathVariable Long id){
        this.businessUnitService.delete(id);
        return"redirect:/businessUnits";
    }
}
