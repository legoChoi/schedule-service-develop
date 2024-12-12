package sparta.scheduleservicedevelop.repository.schedule;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.scheduleservicedevelop.entity.Schedule;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleJpaRepository implements ScheduleRepository {

    private final EntityManager entityManager;

    @Override
    public Schedule save(Schedule schedule) {
        this.entityManager.persist(schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> fetchOneById(Long id) {
        return Optional.ofNullable(this.entityManager.find(Schedule.class, id));
    }

    @Override
    public List<Schedule> fetchAll() {
        return this.entityManager.createQuery("SELECT s FROM Schedule s", Schedule.class)
                .getResultList();
    }

    @Override
    public void delete(Schedule schedule) {
        this.entityManager.remove(schedule);
    }
}
