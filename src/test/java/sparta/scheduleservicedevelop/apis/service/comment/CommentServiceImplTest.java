package sparta.scheduleservicedevelop.apis.service.comment;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.repository.comment.CommentRepository;
import sparta.scheduleservicedevelop.entity.Comment;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImplTest.class);
    @Autowired
    EntityManager entityManager;

    @Autowired
    CommentService commentService;

    @Test
    void createAndFindComment() {
        User user = new User("user", "password", "test@test.com");

        this.entityManager.persist(user);

        User savedUser = this.entityManager.find(User.class, user.getId());

        log.info("savedUser {} name {}", savedUser.getId(), savedUser.getUserName());

        Schedule schedule = new Schedule("title", "contents");
        schedule.setUser(user);

        this.entityManager.persist(schedule);
        this.entityManager.flush();

        Schedule savedSChedule = this.entityManager.find(Schedule.class, schedule.getId());

        log.info("savedUser {} name {}", savedUser.getId(), savedUser.getUserName());
        log.info("savedSchedule {} user {}", savedSChedule.getId(), savedSChedule.getUser().getId());

        Comment comment = new Comment(savedSChedule, savedUser, "comment contents");
        Comment savedComment = this.commentService.createComment(comment);

        entityManager.flush();

        Comment findComment = this.commentService.fetchOneById(savedComment.getId());

        entityManager.flush();

        Assertions.assertThat(savedComment).isEqualTo(findComment);
    }
}