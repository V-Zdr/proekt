package mk.finki.ukim.proekt.repository;

import mk.finki.ukim.proekt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByName(String name);
}
