package iwona.pl.modol9baza.repository;

import iwona.pl.modol9baza.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends JpaRepository<Data, Long> {
}
