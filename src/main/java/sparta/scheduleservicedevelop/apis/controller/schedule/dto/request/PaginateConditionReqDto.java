package sparta.scheduleservicedevelop.apis.controller.schedule.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginateConditionReqDto {

    @PositiveOrZero
    private Integer page;
    @Positive
    private Integer size;
}
