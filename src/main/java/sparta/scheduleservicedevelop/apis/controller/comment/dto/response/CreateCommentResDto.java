package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Comment;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateCommentResDto {

    private final Long commentId;
    private final Long userId;
    private final Long scheduleId;
    private final String contents;

    public static CreateCommentResDto from(Comment comment) {
        return CreateCommentResDto.builder()
                .commentId(comment.getId())
                .userId(comment.getUser().getId())
                .scheduleId(comment.getSchedule().getId())
                .contents(comment.getContents())
                .build();
    }
}
