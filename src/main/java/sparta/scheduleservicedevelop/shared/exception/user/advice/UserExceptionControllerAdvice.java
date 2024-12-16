package sparta.scheduleservicedevelop.shared.exception.user.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.scheduleservicedevelop.apis.controller.user.UserController;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionControllerAdvice {

    @ExceptionHandler(UserPasswordMismatchException.class)
    public ResponseEntity<ExceptionDto> userPasswordMismatchException(UserPasswordMismatchException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorCode())
                .body(exceptionDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorCode())
                .body(exceptionDto);
    }

    @ExceptionHandler(AlreadyExistsUserEmailException.class)
    public ResponseEntity<ExceptionDto> alreadyExistsUserEmailException(AlreadyExistsUserEmailException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorCode())
                .body(exceptionDto);
    }
}
