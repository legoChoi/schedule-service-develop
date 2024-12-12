package sparta.scheduleservicedevelop.controller.schedule.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleReqDto {
    private String writer;
    private String title;
    private String contents;
}
