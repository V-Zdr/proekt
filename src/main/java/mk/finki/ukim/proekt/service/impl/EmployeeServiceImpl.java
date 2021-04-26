package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.Department;
import mk.finki.ukim.proekt.model.Employee;
import mk.finki.ukim.proekt.model.Task;
import mk.finki.ukim.proekt.model.exceptions.InvalidEmployeeIdException;
import mk.finki.ukim.proekt.repository.DepartmentRepository;
import mk.finki.ukim.proekt.repository.EmployeeRepository;
import mk.finki.ukim.proekt.repository.TaskRepository;
import mk.finki.ukim.proekt.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final TaskRepository taskRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findByName(String name) {
        return this.employeeRepository.findByName(name);
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(()->new InvalidEmployeeIdException(id));
    }

    @Override
    public Employee add(String name, int salary,  Department department,List<Long> tasks) {
        List<Task> tasksList=this.taskRepository.findAllById(tasks);
        Employee employee = new Employee(name,salary,department,tasksList);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, int salary,  Department department,List<Long> tasks) {
        Employee employee=this.findById(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDepartment(department);
        List<Task> tasksList=this.taskRepository.findAllById(tasks);
        employee.setTasks(tasksList);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee=this.findById(id);
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filterEmployeesBySalary(int salary,String filter) {
        List<Employee> employees=this.findAll();
        List<Employee> matchingEmployees = new ArrayList<>();
        if(filter.equals("Less")){
            for(Employee employee: employees){
                if (employee.getSalary()<salary){
                    matchingEmployees.add(employee);
                }
            }
        } else{
            for(Employee employee: employees){
                if (employee.getSalary()>salary){
                    matchingEmployees.add(employee);
                }
            }
        }

        return matchingEmployees;
    }


}
