package ma.enset.patientsdemo.repositories;

import ma.enset.patientsdemo.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findMedecinByNom(String name);
}
