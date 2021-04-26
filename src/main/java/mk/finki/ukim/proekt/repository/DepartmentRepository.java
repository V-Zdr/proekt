package mk.finki.ukim.proekt.repository;

import mk.finki.ukim.proekt.model.Department;
import mk.finki.ukim.proekt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
