// MedicationRepository.java
package PochingLao.Repo;

import org.springframework.data.repository.CrudRepository;
import PochingLao.entity.Medication;
import java.util.List;

public interface MedicationRepository extends CrudRepository<Medication, Integer> {
    List<Medication> findByPatientId(Integer patientId);
}
