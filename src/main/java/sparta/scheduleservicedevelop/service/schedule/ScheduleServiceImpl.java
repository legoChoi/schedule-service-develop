package sparta.scheduleservicedevelop.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.exceptions.ScheduleNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findOneById(Long id) {
        return this.scheduleRepository.fetchOneById(id)
                .orElseThrow(ScheduleNotFoundException::new);
    }

    @Override
    public List<Schedule> findAll() {
        return this.scheduleRepository.fetchAll();
    }

    @Override
    public void updateById(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto) {
        Schedule schedule = this.scheduleRepository.fetchOneById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        schedule.setWriter(updateScheduleReqDto.getWriter());
        schedule.setTitle(updateScheduleReqDto.getTitle());
        schedule.setContents(updateScheduleReqDto.getContents());
    }

    @Override
    public void deleteById(Long scheduleId) {
        Schedule schedule = this.scheduleRepository.fetchOneById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        this.scheduleRepository.delete(schedule);
    }
}
