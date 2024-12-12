package sparta.scheduleservicedevelop.controller.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FetchScheduleListResDto {
    private int count;
    private List<FetchScheduleResDto> data;
}
