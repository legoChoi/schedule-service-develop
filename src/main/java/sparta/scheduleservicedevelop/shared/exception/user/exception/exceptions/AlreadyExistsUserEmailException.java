package sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions;

import sparta.scheduleservicedevelop.shared.exception.user.exception.UserExceptionMessages;

public class AlreadyExistsUserEmailException extends RuntimeException {
    public AlreadyExistsUserEmailException() {
        super(UserExceptionMessages.ALREADY_EXISTS_USER_EMAIL.getErrorMessage());
    }
}
