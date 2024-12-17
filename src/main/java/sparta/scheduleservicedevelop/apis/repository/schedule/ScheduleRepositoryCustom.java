package sparta.scheduleservicedevelop.apis.repository.schedule;


import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepositoryCustom {
    Schedule customSave(Schedule schedule);
    Optional<Schedule> customFindById(Long id);
    List<Schedule> customFindAll();
    void customDelete(Schedule schedule);
}
