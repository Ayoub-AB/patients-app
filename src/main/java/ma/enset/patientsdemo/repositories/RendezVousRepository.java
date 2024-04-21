package ma.enset.patientsdemo.repositories;

import ma.enset.patientsdemo.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
}
