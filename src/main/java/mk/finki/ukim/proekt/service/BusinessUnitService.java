package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.BusinessUnit;

import java.util.List;

public interface BusinessUnitService {
    List<BusinessUnit> findAll();
    BusinessUnit findById(Long id);
    BusinessUnit findByName(String name);
    BusinessUnit add(String name, Long company);
    BusinessUnit update(Long id, String name, Long company);
    BusinessUnit delete(Long id);
}
