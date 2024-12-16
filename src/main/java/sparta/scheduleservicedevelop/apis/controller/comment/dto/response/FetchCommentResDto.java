package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Comment;

@Getter
public class FetchCommentResDto {

    private final Long userId;
    private final Long scheduleId;
    private final String contents;

    @Builder
    public FetchCommentResDto(Long userId, Long scheduleId, String contents) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.contents = contents;
    }

    public static FetchCommentResDto from(Comment comment) {
        return FetchCommentResDto.builder()
                .userId(comment.getUser().getId())
                .scheduleId(comment.getSchedule().getId())
                .contents(comment.getContents())
                .build();
    }
}
