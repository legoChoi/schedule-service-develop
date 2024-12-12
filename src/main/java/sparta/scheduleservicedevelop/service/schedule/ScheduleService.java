package sparta.scheduleservicedevelop.service.schedule;

import sparta.scheduleservicedevelop.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    Schedule findOneById(Long id);

    List<Schedule> findAll();

    void updateById(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);

    void deleteById(Long scheduleId);
}
