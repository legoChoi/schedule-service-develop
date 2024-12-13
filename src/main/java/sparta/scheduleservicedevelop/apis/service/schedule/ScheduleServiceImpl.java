package sparta.scheduleservicedevelop.apis.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;

import java.util.List;

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
