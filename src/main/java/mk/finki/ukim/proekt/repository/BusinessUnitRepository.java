package mk.finki.ukim.proekt.repository;

import mk.finki.ukim.proekt.model.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit,Long> {
    BusinessUnit findByName(String name);

}
