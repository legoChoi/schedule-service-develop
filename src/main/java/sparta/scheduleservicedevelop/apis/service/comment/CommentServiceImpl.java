package sparta.scheduleservicedevelop.apis.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.UpdateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentListResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentResDto;
import sparta.scheduleservicedevelop.apis.repository.comment.CommentRepository;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.entity.Comment;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UnAuthorizedException;
import sparta.scheduleservicedevelop.shared.exception.comment.exception.CommentNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CreateCommentResDto createComment(Long userId, CreateCommentReqDto createCommentReqDto) {
        // 연관관계 매핑
        User user = getUserById(userId);
        Schedule schedule = getScheduleById(createCommentReqDto);
        Comment comment = new Comment(schedule, user, createCommentReqDto.getContents());

        // save
        this.commentRepository.save(comment);
        return CreateCommentResDto.from(comment);
    }

    @Override
    public FetchCommentResDto fetchOneById(Long id) {
        Comment comment = getCommentById(id);
        return FetchCommentResDto.from(comment);
    }

    @Override
    public FetchCommentListResDto fetchAll() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<FetchCommentResDto> data = commentList.stream()
                .map(FetchCommentResDto::from)
                .toList();
        return new FetchCommentListResDto(data.size(), data);
    }

    @Override
    @Transactional
    public void updateComment(Long userId, Long commentId, UpdateCommentReqDto commentReqDto) {
        Comment comment = getCommentById(commentId);
        checkAuth(comment, userId);
        comment.setContents(commentReqDto.getContents());
    }

    @Override
    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = getCommentById(commentId);
        checkAuth(comment, userId);
        this.commentRepository.delete(comment);
    }

    private User getUserById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private Schedule getScheduleById(CreateCommentReqDto createCommentReqDto) {
        return this.scheduleRepository.findById(createCommentReqDto.getScheduleId())
                .orElseThrow(ScheduleNotFoundException::new);
    }

    private Comment getCommentById(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        return comment;
    }

    /**
     * 현재 세션에 있는 로그인 된 유저의 ID와 변경하려는 댓글 데이터의 ID 값의 비교
     * : 다르면 FORBIDDEN Exception
     */
    private void checkAuth(Comment comment, Long userId) {
        if (!comment.getUser().getId().equals(userId)) {
            throw new UnAuthorizedException();
        }
    }
}
