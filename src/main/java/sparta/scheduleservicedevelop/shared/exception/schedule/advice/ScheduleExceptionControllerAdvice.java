package sparta.scheduleservicedevelop.shared.exception.schedule.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.controller.schedule.ScheduleController;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.exceptions.ScheduleNotFoundException;

@RestControllerAdvice(assignableTypes = ScheduleController.class)
public class ScheduleExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> emptyScheduleException(ScheduleNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }
}
