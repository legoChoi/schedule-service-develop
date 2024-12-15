package sparta.scheduleservicedevelop.apis.service.comment;

import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.entity.Comment;

import java.util.List;

public interface CommentService {

    CreateCommentResDto createComment(Long userId, CreateCommentReqDto createCommentReqDto);
    Comment fetchOneById(Long id);
    List<Comment> fetchAll();
    void update(Comment comment);
    void delete(Long id);
}
