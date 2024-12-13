package sparta.scheduleservicedevelop.repository.user;

import sparta.scheduleservicedevelop.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void delete(User user);
}
