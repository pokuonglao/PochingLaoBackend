// AppointmentRepository.java
package PochingLao.Repo;

import org.springframework.data.repository.CrudRepository;
import PochingLao.entity.Appointment;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> findByPatientId(Integer patientId);
}
