package sparta.scheduleservicedevelop.apis.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.request.CreateCommentReqDto;
import sparta.scheduleservicedevelop.apis.controller.comment.dto.response.CreateCommentResDto;
import sparta.scheduleservicedevelop.apis.service.comment.CommentService;

@RestController
@RequestMapping("/apis/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
//
//    @PostMapping
//    public ResponseEntity<CreateCommentResDto> createComment(
//            @RequestBody CreateCommentReqDto createCommentReqDto
//    ) {
//
//
//    }
}
