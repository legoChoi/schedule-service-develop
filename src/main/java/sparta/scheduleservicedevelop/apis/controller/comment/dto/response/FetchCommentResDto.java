package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Comment;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FetchCommentResDto {

    private final Long userId;
    private final Long scheduleId;
    private final String contents;

    public static FetchCommentResDto from(Comment comment) {
        return FetchCommentResDto.builder()
                .userId(comment.getUser().getId())
                .scheduleId(comment.getSchedule().getId())
                .contents(comment.getContents())
                .build();
    }
}
