package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.Employee;
import mk.finki.ukim.proekt.model.Task;
import mk.finki.ukim.proekt.model.exceptions.InvalidTaskIdException;
import mk.finki.ukim.proekt.repository.EmployeeRepository;
import mk.finki.ukim.proekt.repository.TaskRepository;
import mk.finki.ukim.proekt.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElseThrow(()->new InvalidTaskIdException(id));
    }

    @Override
    public Task findByName(String name) {
        return this.taskRepository.findByName(name);
    }

    @Override
    public Task add(String name, String description) {
        Task task=new Task(name,description);
        return this.taskRepository.save(task);
    }

    @Override
    public Task update(Long id, String name, String description) {
        Task task = this.findById(id);
        task.setName(name);
        task.setDescription(description);
        return this.taskRepository.save(task);
    }

    @Override
    public Task delete(Long id) {
        Task task = this.findById(id);
        this.taskRepository.delete(task);
        return task;
    }
}
