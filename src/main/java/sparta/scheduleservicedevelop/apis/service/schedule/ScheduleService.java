package sparta.scheduleservicedevelop.apis.service.schedule;

import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.CreateScheduleResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleListResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleResDto;

public interface ScheduleService {
    CreateScheduleResDto createSchedule(Long userId, CreateScheduleReqDto createScheduleReqDto);
    FetchScheduleResDto fetchOneById(Long id);
    FetchScheduleListResDto fetchAll();
    void updateSchedule(Long userId, Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);
    void deleteSchedule(Long userId, Long scheduleId);
}
