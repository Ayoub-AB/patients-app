package ma.enset.patientsdemo;

import ma.enset.patientsdemo.entities.Patient;
import ma.enset.patientsdemo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientsDemoApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientsDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Ajouter des patients
        for (int i = 0; i < 100; i++) {
            patientRepository.save(new Patient(null, "Ahmed", new Date(), (int) (Math.random() * 1000), Math.random() > 0.5 ? true : false));
        }

        //Consulter les patients
        //PageRequest pour utiliser la pagination pour patients
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1, 10));

        System.out.println("Total elements: " + patients.getTotalElements());
        System.out.println("Total pages" + patients.getTotalPages());
        System.out.println("Num page:" + patients.getNumber());


//        patients.forEach(p->{
//            System.out.println("-----------------------------");
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDateNaissance());
//            System.out.println(p.isMalade());
//        });

        //Le contenu de la page
        List<Patient> content = patients.getContent();

        //Chercher patients qui sont malades
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0, 10));
        byMalade.forEach(p -> {
            System.out.println("-----------------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });
//        Mettre a jour patient
//        patient.setScore(560);
//        patientRepository.save(patient);

        //Supprimer patient par id
        patientRepository.deleteById(2L);
        Patient patient = patientRepository.findById(2L).orElse(null);
        if (patient != null) {
            System.out.println("************************");
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        } else System.out.println("no patient found");


    }
}
