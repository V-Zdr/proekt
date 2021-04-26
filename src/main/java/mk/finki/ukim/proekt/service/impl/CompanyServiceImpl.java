package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.Company;
import mk.finki.ukim.proekt.model.exceptions.InvalidCompanyIdException;
import mk.finki.ukim.proekt.repository.BusinessUnitRepository;
import mk.finki.ukim.proekt.repository.CompanyRepository;
import mk.finki.ukim.proekt.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final BusinessUnitRepository businessUnitRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, BusinessUnitRepository businessUnitRepository) {
        this.companyRepository = companyRepository;
        this.businessUnitRepository = businessUnitRepository;
    }

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(()-> new InvalidCompanyIdException(id));
    }

    @Override
    public Company findByName(String name) {
        return this.companyRepository.findByName(name);
    }

    @Override
    public Company add(String name, String address) {
        Company company = new Company(name,address);
        return this.companyRepository.save(company);
    }

    @Override
    public Company update(Long id, String name, String address) {
        Company company=this.findById(id);
        company.setName(name);
        company.setAddress(address);
        return this.companyRepository.save(company);
    }

    @Override
    public Company delete(Long id) {
        Company company=this.findById(id);
        this.companyRepository.delete(company);
        return company;
    }
}
