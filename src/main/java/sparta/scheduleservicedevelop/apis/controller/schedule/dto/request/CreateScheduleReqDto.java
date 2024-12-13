package sparta.scheduleservicedevelop.apis.controller.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleReqDto {

    @NotNull
    private Long userId;

    @NotBlank @Size(min = 2, max = 20)
    private String title;

    @NotBlank @Size(min = 2, max = 200)
    private String contents;
}
