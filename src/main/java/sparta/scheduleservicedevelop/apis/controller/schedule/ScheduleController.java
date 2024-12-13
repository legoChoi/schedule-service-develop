package sparta.scheduleservicedevelop.apis.controller.schedule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.CreateScheduleResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleListResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleResDto;
import sparta.scheduleservicedevelop.entity.Schedule;
import sparta.scheduleservicedevelop.apis.service.schedule.ScheduleService;
import sparta.scheduleservicedevelop.shared.session.SessionTags;

import java.util.List;

@RestController
@RequestMapping("/apis/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResDto> createSchedule(
            @RequestBody CreateScheduleReqDto createScheduleReqDto,
            HttpServletRequest request
    ) {
        Long userId = getUserIdFromSession(request);

        Schedule schedule = new Schedule(
                createScheduleReqDto.getTitle(),
                createScheduleReqDto.getContents()
        );

        Schedule savedSchedule = this.scheduleService.save(userId, schedule);

        CreateScheduleResDto data = new CreateScheduleResDto(
                savedSchedule.getId(),
                savedSchedule.getUser().getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<FetchScheduleResDto> fetchOne(
            @PathVariable("scheduleId") Long scheduleId
    ) {
        Schedule schedule = this.scheduleService.findOneById(scheduleId);

        FetchScheduleResDto data = new FetchScheduleResDto(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<FetchScheduleListResDto> fetchAll() {
        List<Schedule> data = this.scheduleService.findAll();

        List<FetchScheduleResDto> list = data.stream()
                .map(m -> new FetchScheduleResDto(
                        m.getId(),
                        m.getUser().getId(),
                        m.getTitle(),
                        m.getContents(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()))
                .toList();

        FetchScheduleListResDto dataDto = new FetchScheduleListResDto(list.size(), list);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dataDto);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            UpdateScheduleReqDto updateScheduleReqDto
    ) {
        this.scheduleService.updateById(scheduleId, updateScheduleReqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            HttpServletRequest request
    ) {
        this.scheduleService.deleteById(scheduleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    private Long getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute(SessionTags.LOGIN_USER.getTag());
    }
}
