package mk.finki.ukim.proekt.web;

import mk.finki.ukim.proekt.model.Employee;
import mk.finki.ukim.proekt.model.Task;
import mk.finki.ukim.proekt.service.EmployeeService;
import mk.finki.ukim.proekt.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model){
        List<Task> tasks=this.taskService.findAll();
        model.addAttribute("tasks",tasks);
        List<Employee> employees = this.employeeService.findAll();
        model.addAttribute("employees",employees);
        return "tasks.html";
    }

    @GetMapping("/tasks/add")
    public String addTaskPage(Model model){
        List<Employee> employees=this.employeeService.findAll();
        model.addAttribute("employees",employees);
        return "task-form.html";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskPage(@PathVariable Long id, Model model){
        Task task=this.taskService.findById(id);
        model.addAttribute("task",task);
        List<Employee> employees=this.employeeService.findAll();
        model.addAttribute("employees",employees);
        return "task-form.html";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String name,
                          @RequestParam String description){
        this.taskService.add(name,description);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}")
    public String addCompany(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String description){
        this.taskService.update(id,name,description);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        this.taskService.delete(id);
        return"redirect:/tasks";
    }
}
