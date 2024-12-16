package sparta.scheduleservicedevelop.apis.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.CreateScheduleResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleListResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleResDto;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UnAuthorizedException;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateScheduleResDto createSchedule(Long userId, CreateScheduleReqDto createScheduleReqDto) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Schedule schedule = Schedule.builder()
                .user(user)
                .title(createScheduleReqDto.getTitle())
                .contents(createScheduleReqDto.getContents())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return CreateScheduleResDto.from(savedSchedule);
    }

    @Override
    public FetchScheduleResDto fetchOneById(Long id) {
        Schedule schedule = this.scheduleRepository.findById(id)
                .orElseThrow(ScheduleNotFoundException::new);

        return FetchScheduleResDto.from(schedule);
    }

    @Override
    public FetchScheduleListResDto fetchAll() {
        List<Schedule> scheduleList = this.scheduleRepository.findAll();

        List<FetchScheduleResDto> data = scheduleList.stream()
                .map(FetchScheduleResDto::from)
                .toList();

        return new FetchScheduleListResDto(data.size(), data);
    }

    @Override
    @Transactional
    public void updateSchedule(Long userId, Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto) {
        Schedule schedule = this.scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        checkAuth(schedule, userId);

        schedule.setTitle(updateScheduleReqDto.getTitle());
        schedule.setContents(updateScheduleReqDto.getContents());
    }

    @Override
    @Transactional
    public void deleteSchedule(Long userId, Long scheduleId) {
        Schedule schedule = this.scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        checkAuth(schedule, userId);

        this.scheduleRepository.delete(schedule);
    }

    /**
     * 현재 세션에 있는 로그인 된 유저의 ID와 변경하려는 일정 데이터의 ID 값의 비교
     * : 다르면 FORBIDDEN Exception
     */
    private void checkAuth(Schedule schedule, Long userId) {
        if (!schedule.getUser().getId().equals(userId)) {
            throw new UnAuthorizedException();
        }
    }
}
