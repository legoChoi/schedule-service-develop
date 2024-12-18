package sparta.scheduleservicedevelop.apis.controller.schedule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.CreateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.request.UpdateScheduleReqDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.CreateScheduleResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleResDto;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.PaginateScheduleListResDto;
import sparta.scheduleservicedevelop.apis.service.schedule.ScheduleService;
import sparta.scheduleservicedevelop.shared.session.SessionUserUtils;

@RestController
@RequestMapping("/apis/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResDto> createSchedule(
            @Valid @RequestBody CreateScheduleReqDto createScheduleReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        CreateScheduleResDto data = this.scheduleService.createSchedule(userId, createScheduleReqDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<FetchScheduleResDto> fetchOne(
            @PathVariable("scheduleId") Long scheduleId
    ) {
        FetchScheduleResDto data = this.scheduleService.fetchOneById(scheduleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<PaginateScheduleListResDto> fetchAll(
            @RequestParam(required = false, defaultValue = "0", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size
    ) {
        PaginateScheduleListResDto data
                = this.scheduleService.fetchAllPaginationWithComments(page, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody UpdateScheduleReqDto updateScheduleReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        this.scheduleService.updateSchedule(userId, scheduleId, updateScheduleReqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        this.scheduleService.deleteSchedule(userId, scheduleId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
