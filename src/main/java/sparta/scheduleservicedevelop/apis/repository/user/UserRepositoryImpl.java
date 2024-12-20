package sparta.scheduleservicedevelop.apis.repository.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.scheduleservicedevelop.entity.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public User customSave(User user) {
        this.entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> customFindById(Long id) {
        return Optional.ofNullable(this.entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> customFindByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = this.entityManager.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList();

        return users.stream().findFirst();
    }

    @Override
    public void customDelete(User user) {
        this.entityManager.remove(user);
    }
}
