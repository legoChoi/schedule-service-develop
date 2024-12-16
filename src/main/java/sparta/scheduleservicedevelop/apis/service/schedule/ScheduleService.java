package sparta.scheduleservicedevelop.apis.service.schedule;

import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(Long userId, Schedule schedule);
    Schedule fetchOneById(Long id);
    List<Schedule> fetchAll();
    void updateSchedule(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);
    void deleteSchedule(Long scheduleId);
}
