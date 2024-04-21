package ma.enset.patientsdemo;

import ma.enset.patientsdemo.entities.*;
import ma.enset.patientsdemo.repositories.ConsultationRepository;
import ma.enset.patientsdemo.repositories.MedecinRepository;
import ma.enset.patientsdemo.repositories.PatientRepository;
import ma.enset.patientsdemo.repositories.RendezVousRepository;
import ma.enset.patientsdemo.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaTypeEditor;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientsDemoApplication{

//    @Autowired
//    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientsDemoApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository){
        return args -> {
            Stream.of("Ahmed","Hassan","Aymane").
                    forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setScore(152);
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });
            Stream.of("Mohamed","Anass","Khadija").
                    forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        hospitalService.saveMedecin(medecin);
                    });
            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1=patientRepository.findPatientByNom("Hassan");

            Medecin medecin=medecinRepository.findMedecinByNom("Anass");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient1);
            hospitalService.saveRDV(rendezVous);

            RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);

            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de consultation......");
            hospitalService.saveConsultation(consultation);

        };
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        Ajouter des patients
        for (int i = 0; i < 100; i++) {
            patientRepository.save(new Patient(null, "Ahmed", new Date(), (int) (Math.random() * 1000), Math.random() > 0.5 ? true : false));
        }

        Consulter les patients
        PageRequest pour utiliser la pagination pour patients
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1, 10));

        System.out.println("Total elements: " + patients.getTotalElements());
        System.out.println("Total pages" + patients.getTotalPages());
        System.out.println("Num page:" + patients.getNumber());


        patients.forEach(p->{
            System.out.println("-----------------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });

        Le contenu de la page
        List<Patient> content = patients.getContent();

        Chercher patients qui sont malades
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0, 10));
        byMalade.forEach(p -> {
            System.out.println("-----------------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });
        Mettre a jour patient
        patient.setScore(560);
        patientRepository.save(patient);

        Supprimer patient par id
        patientRepository.deleteById(2L);
        Patient patient = patientRepository.findById(2L).orElse(null);
        if (patient != null) {
            System.out.println("************************");
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        } else System.out.println("no patient found");

    } */
}
