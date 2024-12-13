package sparta.scheduleservicedevelop.controller.schedule.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleReqDto {
    private Long userId;
    private String title;
    private String contents;
}
