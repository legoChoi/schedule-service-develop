package sparta.scheduleservicedevelop.apis.controller.schedule.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class PaginateScheduleListResDto {

    private final int totalPages;
    private final Long totalElements;
    private final List<FetchScheduleListJoinResDto> data;
}
