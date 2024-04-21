package ma.enset.patientsdemo.repositories;

import ma.enset.patientsdemo.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
