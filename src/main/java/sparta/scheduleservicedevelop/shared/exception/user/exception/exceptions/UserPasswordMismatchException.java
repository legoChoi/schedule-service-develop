package sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions;


import sparta.scheduleservicedevelop.shared.exception.user.exception.UserExceptionMessages;

public class UserPasswordMismatchException extends RuntimeException {
    public UserPasswordMismatchException() {
        super(UserExceptionMessages.USER_PASSWORD_MISMATCH.getErrorMessage());
    }
}
