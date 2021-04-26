package mk.finki.ukim.proekt.repository;

import mk.finki.ukim.proekt.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByName(String name);
}
