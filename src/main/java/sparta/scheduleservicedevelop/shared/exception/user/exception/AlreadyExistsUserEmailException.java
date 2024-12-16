package sparta.scheduleservicedevelop.shared.exception.user.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.user.exception.message.UserExceptionMessages;

@Getter
public class AlreadyExistsUserEmailException extends RuntimeException {

    private final int errorCode = UserExceptionMessages.ALREADY_EXISTS_USER_EMAIL.getErrorCode();

    public AlreadyExistsUserEmailException() {
        super(UserExceptionMessages.ALREADY_EXISTS_USER_EMAIL.getErrorMessage());
    }
}
