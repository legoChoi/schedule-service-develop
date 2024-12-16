package sparta.scheduleservicedevelop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.apis.service.schedule.ScheduleService;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleServiceImplTest {

    @Autowired ScheduleRepository scheduleRepository;
    @Autowired ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
        this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
        this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
        this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
        this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
    }

    @Test
    void save() {
        Schedule schedule = new Schedule("title", "contents");
        Schedule savedSchedule = this.scheduleService.createSchedule(null, schedule);

        assertThat(schedule).isEqualTo(savedSchedule);
    }

    @Test
    void find() {
        Schedule savedSchedule = this.scheduleService.createSchedule(null, new Schedule("title", "contents"));
        Schedule findSchedule = this.scheduleService.fetchOneById(savedSchedule.getId());

        assertThat(savedSchedule).isEqualTo(findSchedule);
    }

    @Test
    void findScheduleNotFoundException() {
        Assertions.assertThrows(ScheduleNotFoundException.class, () ->
            this.scheduleService.fetchOneById(999L));
    }

    @Test
    void findAll() {
        List<Schedule> schedules = this.scheduleService.fetchAll();

        assertThat(schedules).hasSize(5);
    }

    @Test
    void delete() {
        List<Schedule> before = this.scheduleService.fetchAll();
        assertThat(before).hasSize(5);

        this.scheduleService.deleteSchedule(1L);

        List<Schedule> after = this.scheduleService.fetchAll();
        assertThat(after).hasSize(4);
    }

    @Test
    void deleteScheduleNotFoundException() {
        Assertions.assertThrows(ScheduleNotFoundException.class, () ->
            this.scheduleService.deleteSchedule(999L));
    }
}