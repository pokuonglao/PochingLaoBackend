// Appointment.java
package PochingLao.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDateTime appointmentTime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Getters and setters
}
