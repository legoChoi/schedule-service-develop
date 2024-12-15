package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCommentResDto {

    private Long userId;
    private Long scheduleId;
    private String contents;
}
