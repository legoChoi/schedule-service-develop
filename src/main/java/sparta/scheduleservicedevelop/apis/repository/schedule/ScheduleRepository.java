package sparta.scheduleservicedevelop.apis.repository.schedule;


import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAll();
    void delete(Schedule schedule);
}
