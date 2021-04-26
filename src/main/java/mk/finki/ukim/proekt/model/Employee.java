package mk.finki.ukim.proekt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int salary;

    @ManyToOne
    private Department department;

    @ManyToMany
    private List<Task> tasks;

    public Employee(){

    }

    public Employee(String name, int salary, Department department,List<Task> tasks) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.tasks=tasks;
    }
}
