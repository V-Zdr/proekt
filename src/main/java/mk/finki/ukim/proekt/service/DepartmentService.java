package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    Department findByName(String name);
    Department add(String name, Long unit);
    Department update(Long id,String name,Long unit);
    Department delete(Long id);
}
