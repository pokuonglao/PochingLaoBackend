package PochingLao.Repo;

import PochingLao.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    Optional<Messages> findById(Long id);
}
