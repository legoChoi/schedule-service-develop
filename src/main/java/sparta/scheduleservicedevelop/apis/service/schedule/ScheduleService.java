package sparta.scheduleservicedevelop.apis.service.schedule;

import org.springframework.data.domain.PageRequest;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.*;


public interface ScheduleService {
    CreateScheduleResDto createSchedule(Long userId, CreateScheduleReqDto createScheduleReqDto);
    FetchScheduleResDto fetchOneById(Long id);
    FetchScheduleListResDto fetchAll();
    void updateSchedule(Long userId, Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);
    void deleteSchedule(Long userId, Long scheduleId);
    PaginateScheduleListResDto fetchAllPaginationWithComments(int page, int size);
}
