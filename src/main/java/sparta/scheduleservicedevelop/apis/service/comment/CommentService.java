package sparta.scheduleservicedevelop.apis.service.comment;

import sparta.scheduleservicedevelop.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Comment comment);
    Comment fetchOneById(Long id);
    List<Comment> fetchAll();
    void update(Comment comment);
    void delete(Long id);
}
