package sparta.scheduleservicedevelop.apis.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.scheduleservicedevelop.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String username);
}
