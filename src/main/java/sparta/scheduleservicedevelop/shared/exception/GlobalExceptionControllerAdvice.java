package sparta.scheduleservicedevelop.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UnAuthorizedException;
import sparta.scheduleservicedevelop.shared.exception.comment.exception.CommentNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidFieldErrorDto;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ExceptionDto> commentNotFoundException(CommentNotFoundException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ExceptionDto> scheduleNotFoundException(ScheduleNotFoundException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsUserEmailException.class)
    public ResponseEntity<ExceptionDto> alreadyExistsUserEmailException(AlreadyExistsUserEmailException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(UserPasswordMismatchException.class)
    public ResponseEntity<ExceptionDto> userPasswordMismatchException(UserPasswordMismatchException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ExceptionDto> unAuthorizedException(UnAuthorizedException e) {
        return buildExceptionResponse(e.getClass().getSimpleName(), e.getErrorCode(), e.getMessage());
    }

    /**
     * Validation 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptionDto> checkValidRequestArgs(MethodArgumentNotValidException e) {
        List<ValidFieldErrorDto> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidFieldErrorDto(
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()
                ))
                .toList();
        log.warn("[{}][{}][{}]", e.getStatusCode().value(), e.getMessage(), e.getClass().getSimpleName());

        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ValidExceptionDto(LocalDateTime.now(), e.getClass().getSimpleName(), e.getStatusCode().value(), fieldErrors));
    }

    /**
     * 예외 응답 객체 빌드
     */
    private ResponseEntity<ExceptionDto> buildExceptionResponse(String exceptionName, int errorCode, String message) {
        log.warn("[{}][{}][{}]", errorCode, message, exceptionName);
        return ResponseEntity
                .status(errorCode)
                .body(new ExceptionDto(LocalDateTime.now(), exceptionName, errorCode, message));
    }
}
