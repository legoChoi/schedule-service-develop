package sparta.scheduleservicedevelop.apis.controller.comment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.UpdateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentListResDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.FetchCommentResDto;
import sparta.scheduleservicedevelop.apis.service.comment.CommentService;
import sparta.scheduleservicedevelop.shared.session.SessionUserInfo;

import java.util.List;

@RestController
@RequestMapping("/apis/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResDto> createComment(
            @Valid @RequestBody CreateCommentReqDto createCommentReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserInfo.getId(request);

        CreateCommentResDto data = this.commentService.createComment(userId, createCommentReqDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<FetchCommentResDto> fetchOne(
            @PathVariable("commentId") Long commentId
    ) {
        FetchCommentResDto data = this.commentService.fetchOneById(commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<FetchCommentListResDto> fetchAll() {

        FetchCommentListResDto data = this.commentService.fetchAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody UpdateCommentReqDto commentReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserInfo.getId(request);

        this.commentService.updateComment(userId, commentId, commentReqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("commentId") Long commentId,
            HttpServletRequest request
    ) {
        Long userId = SessionUserInfo.getId(request);

        this.commentService.deleteComment(userId, commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
