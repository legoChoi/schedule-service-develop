package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FetchCommentResDto {

    private final Long commentId;
    private final Long userId;
    private final Long scheduleId;
    private final String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FetchCommentResDto from(Comment comment) {
        return FetchCommentResDto.builder()
                .commentId(comment.getId())
                .userId(comment.getUser().getId())
                .scheduleId(comment.getSchedule().getId())
                .contents(comment.getContents())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
