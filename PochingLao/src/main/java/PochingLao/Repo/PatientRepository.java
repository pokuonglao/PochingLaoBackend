// PatientRepository.java
package PochingLao.Repo;

import org.springframework.data.repository.CrudRepository;
import PochingLao.entity.Patient;
import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {}
