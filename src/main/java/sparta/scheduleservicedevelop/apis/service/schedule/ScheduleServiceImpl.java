package sparta.scheduleservicedevelop.apis.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public Schedule save(Long userId, Schedule schedule) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        schedule.setUser(user);

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findOneById(Long id) {
        return this.scheduleRepository.findById(id)
                .orElseThrow(ScheduleNotFoundException::new);
    }

    @Override
    public List<Schedule> findAll() {
        return this.scheduleRepository.findAll();
    }

    @Override
    public void updateById(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto) {
        Schedule schedule = this.scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        schedule.setTitle(updateScheduleReqDto.getTitle());
        schedule.setContents(updateScheduleReqDto.getContents());
    }

    @Override
    public void deleteById(Long scheduleId) {
        Schedule schedule = this.scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        this.scheduleRepository.delete(schedule);
    }
}
