package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class FetchScheduleResDto {
    private Long scheduleId;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder(access = AccessLevel.PRIVATE)
    public FetchScheduleResDto(Long scheduleId, Long userId, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static FetchScheduleResDto from(Schedule schedule) {
        return FetchScheduleResDto.builder()
                .scheduleId(schedule.getId())
                .userId(schedule.getUser().getId())
                .title(schedule.getTitle())
                .contents(schedule.getContents())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }
}
