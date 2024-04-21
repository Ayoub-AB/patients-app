package ma.enset.patientsdemo.service;

import ma.enset.patientsdemo.entities.Consultation;
import ma.enset.patientsdemo.entities.Medecin;
import ma.enset.patientsdemo.entities.Patient;
import ma.enset.patientsdemo.entities.RendezVous;
import ma.enset.patientsdemo.repositories.ConsultationRepository;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
