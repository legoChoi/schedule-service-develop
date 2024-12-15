package sparta.scheduleservicedevelop.apis.controller.comment;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.apis.service.comment.CommentService;
import sparta.scheduleservicedevelop.shared.session.SessionUserInfo;

@RestController
@RequestMapping("/apis/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResDto> createComment(
            @RequestBody CreateCommentReqDto createCommentReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserInfo.getId(request);

        CreateCommentResDto data = this.commentService.createComment(userId, createCommentReqDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }
}
