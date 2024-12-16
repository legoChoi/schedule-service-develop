package sparta.scheduleservicedevelop.shared.exception.schedule.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.apis.controller.schedule.ScheduleController;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;

@RestControllerAdvice(assignableTypes = ScheduleController.class)
public class ScheduleExceptionControllerAdvice {

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ExceptionDto> emptyScheduleException(ScheduleNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorCode())
                .body(exceptionDto);
    }
}
