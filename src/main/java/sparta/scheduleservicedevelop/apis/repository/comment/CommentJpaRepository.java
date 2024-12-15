package sparta.scheduleservicedevelop.apis.repository.comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.scheduleservicedevelop.entity.Comment;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentJpaRepository implements CommentRepository {

    private final EntityManager entityManager;

    @Override
    public void save(Comment comment) {
        this.entityManager.persist(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(this.entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        return this.entityManager.createQuery("SELECT c FROM Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public void delete(Comment comment) {
        this.entityManager.remove(comment);
    }
}
