package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task findByName(String name);
    Task add(String name, String description);
    Task update(Long id,String name,String description);
    Task delete(Long id);
}
