package sparta.scheduleservicedevelop.apis.repository.user;

import sparta.scheduleservicedevelop.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    User customSave(User user);
    Optional<User> customFindById(Long id);
    Optional<User> customFindByEmail(String email);
    void customDelete(User user);
}
