package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class CreateScheduleResDto {
    private Long scheduleId;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}