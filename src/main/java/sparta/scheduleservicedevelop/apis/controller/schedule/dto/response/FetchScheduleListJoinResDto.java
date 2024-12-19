package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FetchScheduleListJoinResDto {
    private final String title;
    private final String contents;
    private final String userName;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
