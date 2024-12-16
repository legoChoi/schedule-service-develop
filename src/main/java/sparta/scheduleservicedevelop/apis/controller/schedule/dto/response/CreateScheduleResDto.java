package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResDto {
    private final Long scheduleId;
    private final Long userId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder(access = AccessLevel.PRIVATE)
    public CreateScheduleResDto(Long scheduleId, Long userId, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateScheduleResDto from(Schedule schedule) {
        return CreateScheduleResDto.builder()
                .scheduleId(schedule.getId())
                .userId(schedule.getUser().getId())
                .title(schedule.getTitle())
                .contents(schedule.getContents())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }
}
