package sparta.scheduleservicedevelop.apis.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.repository.comment.CommentRepository;
import sparta.scheduleservicedevelop.entity.Comment;
import sparta.scheduleservicedevelop.shared.exception.comment.exception.CommentNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment fetchOneById(Long id) {
        return this.commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public List<Comment> fetchAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Long id) {

    }
}
