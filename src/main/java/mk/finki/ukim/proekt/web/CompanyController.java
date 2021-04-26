package mk.finki.ukim.proekt.web;

import mk.finki.ukim.proekt.model.Company;
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
public class CompanyController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final BusinessUnitService businessUnitService;
    private final CompanyService companyService;

    public CompanyController(EmployeeService employeeService, DepartmentService departmentService, BusinessUnitService businessUnitService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.businessUnitService = businessUnitService;
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public String getCompanies(Model model){
        List<Company> companies=this.companyService.findAll();
        model.addAttribute("companies",companies);
        return "companies.html";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage(Model model){
        return "company-form.html";
    }

    @GetMapping("/companies/{id}/edit")
    public String editCompanyPage(@PathVariable Long id,Model model){
        Company company=this.companyService.findById(id);
        model.addAttribute("company",company);
        return "company-form.html";
    }

    @PostMapping("/companies")
    public String addCompany(@RequestParam String name,
                             @RequestParam String address){
        this.companyService.add(name,address);
        return "redirect:/companies";
    }

    @PostMapping("/companies/{id}")
    public String editCompany(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String address){
        this.companyService.update(id,name,address);
        return "redirect:/companies";
    }

    @PostMapping("/companies/{id}/delete")
    public String deleteCompany(@PathVariable Long id){
        this.companyService.delete(id);
        return"redirect:/companies";
    }
}
