package mk.finki.ukim.proekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private BusinessUnit businessUnit;

    public Department(){

    }

    public Department(String name, BusinessUnit businessUnit){
        this.name=name;
        this.businessUnit = businessUnit;
    }

}
