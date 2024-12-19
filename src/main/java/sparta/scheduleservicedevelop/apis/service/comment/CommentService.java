package sparta.scheduleservicedevelop.apis.service.comment;

import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.UpdateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentListResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentResDto;

public interface CommentService {

    CreateCommentResDto createComment(Long userId, CreateCommentReqDto createCommentReqDto);
    FetchCommentResDto fetchOneById(Long id);
    FetchCommentListResDto fetchAll();
    void updateComment(Long userId, Long commentId, UpdateCommentReqDto commentReqDto);
    void deleteComment(Long userId, Long commentId);
}
