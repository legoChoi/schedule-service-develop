package sparta.scheduleservicedevelop.apis.service.schedule;

import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Long userId, Schedule schedule);
    Schedule findOneById(Long id);
    List<Schedule> findAll();
    void updateById(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);
    void deleteById(Long scheduleId);
}
