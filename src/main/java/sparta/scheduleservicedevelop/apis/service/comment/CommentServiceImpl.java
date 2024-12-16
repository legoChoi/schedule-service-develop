package sparta.scheduleservicedevelop.apis.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.UpdateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
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
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Schedule schedule = this.scheduleRepository.findById(createCommentReqDto.getScheduleId())
                .orElseThrow(ScheduleNotFoundException::new);

        Comment createComment = new Comment(schedule, user, createCommentReqDto.getContents());

        this.commentRepository.save(createComment);

        return new CreateCommentResDto(
                createComment.getUser().getId(),
                createComment.getSchedule().getId(),
                createComment.getContents()
        );
    }

    @Override
    public Comment fetchOneById(Long id) {
        return this.commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public List<Comment> fetchAll() {
        return this.commentRepository.findAll();
    }

    @Override
    @Transactional
    public void updateComment(Long userId, Long commentId, UpdateCommentReqDto commentReqDto) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        checkAuth(comment, userId);

        comment.setContents(commentReqDto.getContents());
    }

    @Override
    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        checkAuth(comment, userId);

        this.commentRepository.delete(comment);
    }

    private void checkAuth(Comment comment, Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (comment.getUser() != user) {
            throw new UnAuthorizedException();
        }
    }
}
