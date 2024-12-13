package sparta.scheduleservicedevelop.repository.schedule;


import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> fetchOneById(Long id);
    List<Schedule> fetchAll();
    void delete(Schedule schedule);
}
