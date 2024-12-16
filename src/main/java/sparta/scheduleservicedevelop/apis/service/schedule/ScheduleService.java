package sparta.scheduleservicedevelop.apis.service.schedule;

import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.CreateScheduleResDto;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    CreateScheduleResDto createSchedule(Long userId, CreateScheduleReqDto createScheduleReqDto);
    Schedule fetchOneById(Long id);
    List<Schedule> fetchAll();
    void updateSchedule(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);
    void deleteSchedule(Long scheduleId);
}
