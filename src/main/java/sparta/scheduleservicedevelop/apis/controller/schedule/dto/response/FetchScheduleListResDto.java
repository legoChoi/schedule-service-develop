package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FetchScheduleListResDto {
    private final int count;
    private final List<FetchScheduleResDto> data;
}
