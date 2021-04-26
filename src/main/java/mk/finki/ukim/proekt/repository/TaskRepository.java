package mk.finki.ukim.proekt.repository;

import mk.finki.ukim.proekt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByName(String name);
}
