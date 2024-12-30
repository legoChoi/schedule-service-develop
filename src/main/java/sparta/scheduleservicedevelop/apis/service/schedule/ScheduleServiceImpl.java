package sparta.scheduleservicedevelop.apis.service.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.*;
import sparta.scheduleservicedevelop.apis.repository.schedule.ScheduleRepository;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UnAuthorizedException;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateScheduleResDto createSchedule(Long userId, CreateScheduleReqDto createScheduleReqDto) {
        User user = getUserById(userId);
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
        Schedule schedule = getScheduleById(id);
        return FetchScheduleResDto.from(schedule);
    }

    @Override
    public FetchScheduleListResDto fetchAll() {
        List<Schedule> scheduleList = this.scheduleRepository.customFindAll();
        List<FetchScheduleResDto> data = scheduleList.stream()
                .map(FetchScheduleResDto::from)
                .toList();
        return new FetchScheduleListResDto(data.size(), data);
    }

    @Override
    public PaginateScheduleListResDto fetchAllPaginationWithComments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt");
        Page<FetchScheduleListJoinResDto> result = this.scheduleRepository.findScheduleAllCountBy(pageRequest);
        return PaginateScheduleListResDto.builder()
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @Override
    @Transactional
    public void updateSchedule(Long userId, Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto) {
        Schedule schedule = getScheduleById(scheduleId);
        checkAuth(schedule, userId);
        schedule.updateTitleAndContents(updateScheduleReqDto.getTitle(), updateScheduleReqDto.getContents());
    }

    @Override
    @Transactional
    public void deleteSchedule(Long userId, Long scheduleId) {
        Schedule schedule = getScheduleById(scheduleId);
        checkAuth(schedule, userId);
        this.scheduleRepository.customDelete(schedule);
    }

    private User getUserById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private Schedule getScheduleById(Long scheduleId) {
        return this.scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);
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
