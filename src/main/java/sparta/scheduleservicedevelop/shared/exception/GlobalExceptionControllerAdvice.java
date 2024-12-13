package sparta.scheduleservicedevelop.shared.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.dto.ValidFieldErrorDto;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler
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
                .badRequest()
                .body(validExceptionDto);
    }
}
