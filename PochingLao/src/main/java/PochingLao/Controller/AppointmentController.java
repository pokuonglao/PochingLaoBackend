//// AppointmentController.java
//package PochingLao.Controller;
//
//import PochingLao.entity.Appointment;
//import PochingLao.Repo.AppointmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentRepository appointmentRepository;
//
//    @GetMapping("/patient/{patientId}")
//    public List<Appointment> getAppointmentsByPatient(@PathVariable Integer patientId) {
//        return appointmentRepository.findByPatientId(patientId);
//    }
//
//    @PostMapping
//    public Appointment addAppointment(@RequestBody Appointment appointment) {
//        return appointmentRepository.save(appointment);
//    }
//}
