package guru.springframework.demo.repository;

import guru.springframework.demo.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);
}
