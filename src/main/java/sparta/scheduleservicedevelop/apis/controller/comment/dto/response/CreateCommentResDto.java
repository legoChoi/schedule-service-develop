package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CreateCommentResDto {

    private Long userId;
    private Long scheduleId;
    private String contents;
}
