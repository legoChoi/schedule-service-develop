package sparta.scheduleservicedevelop.apis.repository.schedule;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Schedule customSave(Schedule schedule) {
        this.entityManager.persist(schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> customFindById(Long id) {
        return Optional.ofNullable(this.entityManager.find(Schedule.class, id));
    }

    @Override
    public List<Schedule> customFindAll() {
        return this.entityManager.createQuery("SELECT s FROM Schedule s", Schedule.class)
                .getResultList();
    }

    @Override
    public void customDelete(Schedule schedule) {
        this.entityManager.remove(schedule);
    }
}
