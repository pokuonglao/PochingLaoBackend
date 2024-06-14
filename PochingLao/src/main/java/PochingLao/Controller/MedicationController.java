//// MedicationController.java
//package PochingLao.Controller;
//
//import PochingLao.entity.Medication;
//import PochingLao.Repo.MedicationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/medications")
//public class MedicationController {
//
//    @Autowired
//    private MedicationRepository medicationRepository;
//
//    @GetMapping("/patient/{patientId}")
//    public List<Medication> getMedicationsByPatient(@PathVariable Integer patientId) {
//        return medicationRepository.findByPatientId(patientId);
//    }
//
//    @PostMapping
//    public Medication addMedication(@RequestBody Medication medication) {
//        return medicationRepository.save(medication);
//    }
//}
