package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.BusinessUnit;
import mk.finki.ukim.proekt.model.Department;
import mk.finki.ukim.proekt.model.exceptions.InvalidBusinessUnitIdException;
import mk.finki.ukim.proekt.model.exceptions.InvalidDepartmentIdException;
import mk.finki.ukim.proekt.repository.BusinessUnitRepository;
import mk.finki.ukim.proekt.repository.DepartmentRepository;
import mk.finki.ukim.proekt.repository.EmployeeRepository;
import mk.finki.ukim.proekt.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final BusinessUnitRepository businessUnitRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, BusinessUnitRepository businessUnitRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.businessUnitRepository = businessUnitRepository;
    }

    @Override
    public List<Department> findAll(){
        return this.departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return this.departmentRepository.findById(id).orElseThrow(()-> new InvalidDepartmentIdException(id));
    }

    @Override
    public Department findByName(String name) {
        return this.departmentRepository.findByName(name);
    }

    @Override
    public Department add(String name, Long unit) {
        BusinessUnit u=this.businessUnitRepository.findById(unit).orElseThrow(()->new InvalidBusinessUnitIdException(unit));
        Department department=new Department(name,u);
        return this.departmentRepository.save(department);
    }

    @Override
    public Department update(Long id, String name, Long unit) {
        Department department=this.findById(id);
        BusinessUnit u=this.businessUnitRepository.findById(unit).orElseThrow(()->new InvalidBusinessUnitIdException(unit));
        department.setName(name);
        department.setBusinessUnit(u);
        return this.departmentRepository.save(department);
    }

    @Override
    public Department delete(Long id) {
        Department department=this.findById(id);
        this.departmentRepository.delete(department);
        return department;
    }
}
