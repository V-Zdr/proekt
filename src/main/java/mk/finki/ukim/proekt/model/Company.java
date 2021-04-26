package mk.finki.ukim.proekt.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
