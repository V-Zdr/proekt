package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findById(Long id);
    Company findByName(String name);
    Company add(String name,String address);
    Company update(Long id,String name,String address);
    Company delete(Long id);
}
