package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.Department;
import mk.finki.ukim.proekt.model.Employee;

import java.util.List;

public interface EmployeeService {
     List<Employee> findAll();
     Employee findByName(String text);
     Employee findById(Long id);
     Employee add(String name,int salary, Department department,List<Long> tasks);
     Employee update(Long id, String name, int salary, Department department,List<Long> tasks);
     Employee delete(Long id);
     List<Employee> filterEmployeesBySalary(int salary,String filter);
}
