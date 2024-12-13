package sparta.scheduleservicedevelop.repository.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.scheduleservicedevelop.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJpaRepository implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        this.entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(this.entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = entityManager.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList();

        return users.stream().findFirst();
    }

    @Override
    public void delete(User user) {
        this.entityManager.remove(user);
    }
}
