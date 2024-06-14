// Medication.java
package PochingLao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String dosage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Getters and setters
}
