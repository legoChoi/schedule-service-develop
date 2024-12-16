package sparta.scheduleservicedevelop.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UnAuthorizedException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidFieldErrorDto;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ExceptionDto> notAuthenticatedException(NotAuthenticatedException e) {
        return ResponseEntity
                .status(e.getErrorCode())
                .body(new ExceptionDto(
                        e.getErrorCode(), e.getMessage()
                ));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ExceptionDto> unAuthorizedException(UnAuthorizedException e) {
        return ResponseEntity
                .status(e.getErrorCode())
                .body(new ExceptionDto(
                        e.getErrorCode(), e.getMessage()
                ));
    }

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

        ValidExceptionDto validExceptionDto = new ValidExceptionDto(e.getStatusCode().value(), fieldErrors);

        return ResponseEntity
                .status(e.getStatusCode())
                .body(validExceptionDto);
    }
}
