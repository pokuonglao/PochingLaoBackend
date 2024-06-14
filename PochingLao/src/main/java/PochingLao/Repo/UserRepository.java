package PochingLao.Repo;

import PochingLao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
}
