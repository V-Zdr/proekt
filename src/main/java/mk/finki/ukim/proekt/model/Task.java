package mk.finki.ukim.proekt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employees;

    public Task(){

    }

    public Task(String name, String description, List<Employee> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
