package sparta.scheduleservicedevelop.shared.exception.comment.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sparta.scheduleservicedevelop.apis.controller.comment.CommentController;
import sparta.scheduleservicedevelop.shared.exception.comment.exception.CommentNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

@ControllerAdvice(assignableTypes = CommentController.class)
public class CommentExceptionControllerAdvice {

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ExceptionDto> commentNotFoundException(CommentNotFoundException e) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException e) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ExceptionDto> scheduleNotFoundException(ScheduleNotFoundException e) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage());
    }

    private static ResponseEntity<ExceptionDto> buildExceptionResponse(int errorCode, String message) {
        return ResponseEntity
                .status(errorCode)
                .body(new ExceptionDto(errorCode, message));
    }
}
