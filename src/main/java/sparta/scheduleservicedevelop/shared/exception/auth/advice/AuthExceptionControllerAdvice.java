package sparta.scheduleservicedevelop.shared.exception.auth.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;

@RestControllerAdvice
public class AuthExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> notAuthenticatedException(NotAuthenticatedException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorCode())
                .body(exceptionDto);
    }
}
