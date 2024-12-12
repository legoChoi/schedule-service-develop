package sparta.scheduleservicedevelop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.service.schedule.ScheduleService;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.exceptions.ScheduleNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleServiceImplTest {

    @Autowired ScheduleRepository scheduleRepository;
    @Autowired ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        this.scheduleService.save(new Schedule("t1", "title", "contents"));
        this.scheduleService.save(new Schedule("t2", "title", "contents"));
        this.scheduleService.save(new Schedule("t3", "title", "contents"));
        this.scheduleService.save(new Schedule("t4", "title", "contents"));
        this.scheduleService.save(new Schedule("t5", "title", "contents"));
    }

    @Test
    void save() {
        Schedule schedule = new Schedule("test", "title", "contents");
        Schedule savedSchedule = this.scheduleService.save(schedule);

        assertThat(schedule).isEqualTo(savedSchedule);
    }

    @Test
    void find() {
        Schedule savedSchedule = this.scheduleService.save(new Schedule("t6", "title", "contents"));
        Schedule findSchedule = this.scheduleService.findOneById(savedSchedule.getId());

        assertThat(savedSchedule).isEqualTo(findSchedule);
    }

    @Test
    void findScheduleNotFoundException() {
        Assertions.assertThrows(ScheduleNotFoundException.class, () ->
            this.scheduleService.findOneById(999L));
    }

    @Test
    void findAll() {
        List<Schedule> schedules = this.scheduleService.findAll();

        assertThat(schedules).hasSize(5);
    }

    @Test
    void delete() {
        List<Schedule> before = this.scheduleService.findAll();
        assertThat(before).hasSize(5);

        this.scheduleService.deleteById(1L);

        List<Schedule> after = this.scheduleService.findAll();
        assertThat(after).hasSize(4);
    }

    @Test
    void deleteScheduleNotFoundException() {
        Assertions.assertThrows(ScheduleNotFoundException.class, () ->
            this.scheduleService.deleteById(999L));
    }
}