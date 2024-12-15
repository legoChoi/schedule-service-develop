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
    }
}