package mk.finki.ukim.proekt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Id;

@Data
@Entity
public class BusinessUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Company company;

    public BusinessUnit(String name, Company company) {
        this.name = name;
        this.company=company;
    }

    public BusinessUnit(){}
}
