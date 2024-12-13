package sparta.scheduleservicedevelop.controller.schedule.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleReqDto {

    @Size(min = 2, max = 20)
    private String title;

    @Size(min = 2, max = 200)
    private String contents;
}
