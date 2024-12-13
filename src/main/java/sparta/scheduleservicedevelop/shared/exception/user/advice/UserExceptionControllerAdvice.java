package sparta.scheduleservicedevelop.shared.exception.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.controller.user.UserController;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions.UserPasswordMismatchException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions.UserNotFoundException;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> userPasswordMismatchException(UserPasswordMismatchException e) {
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.UNAUTHORIZED.value(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exceptionDto);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> alreadyExistsUserEmailException(AlreadyExistsUserEmailException e) {
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.CONFLICT.value(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionDto);
    }
}
