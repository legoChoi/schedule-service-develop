package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class PaginateScheduleListResDto {

    private int totalPages;
    private Long totalElements;
    private List<FetchScheduleListJoinResDto> data;
}
