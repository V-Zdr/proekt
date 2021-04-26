package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.BusinessUnit;
import mk.finki.ukim.proekt.model.Company;
import mk.finki.ukim.proekt.model.exceptions.InvalidBusinessUnitIdException;
import mk.finki.ukim.proekt.model.exceptions.InvalidCompanyIdException;
import mk.finki.ukim.proekt.repository.BusinessUnitRepository;
import mk.finki.ukim.proekt.repository.CompanyRepository;
import mk.finki.ukim.proekt.repository.DepartmentRepository;
import mk.finki.ukim.proekt.service.BusinessUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;

    public BusinessUnitServiceImpl(BusinessUnitRepository businessUnitRepository, CompanyRepository companyRepository, DepartmentRepository departmentRepository) {
        this.businessUnitRepository = businessUnitRepository;
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<BusinessUnit> findAll() {
        return this.businessUnitRepository.findAll();
    }

    @Override
    public BusinessUnit findById(Long id) {
        return this.businessUnitRepository.findById(id).orElseThrow(()->new InvalidBusinessUnitIdException(id));
    }

    @Override
    public BusinessUnit findByName(String name) {
        return this.businessUnitRepository.findByName(name);
    }

    @Override
    public BusinessUnit add(String name, Long company) {
        Company c =this.companyRepository.findById(company).orElseThrow(()->new InvalidCompanyIdException(company));
        BusinessUnit businessUnit = new BusinessUnit(name,c);
        return this.businessUnitRepository.save(businessUnit);
    }

    @Override
    public BusinessUnit update(Long id, String name, Long company) {
        BusinessUnit businessUnit = this.findById(id);
        Company c =this.companyRepository.findById(company).orElseThrow(()->new InvalidCompanyIdException(company));
        businessUnit.setName(name);
        businessUnit.setCompany(c);
        return this.businessUnitRepository.save(businessUnit);
    }

    @Override
    public BusinessUnit delete(Long id) {
        BusinessUnit businessUnit = this.findById(id);
        this.businessUnitRepository.delete(businessUnit);
        return businessUnit;
    }
}
