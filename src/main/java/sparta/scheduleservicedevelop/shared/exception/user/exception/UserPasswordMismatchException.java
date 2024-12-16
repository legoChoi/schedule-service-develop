package sparta.scheduleservicedevelop.shared.exception.user.exception;


import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.user.exception.message.UserExceptionMessages;

@Getter
public class UserPasswordMismatchException extends RuntimeException {

    private final int errorCode = UserExceptionMessages.USER_PASSWORD_MISMATCH.getErrorCode();

    public UserPasswordMismatchException() {
        super(UserExceptionMessages.USER_PASSWORD_MISMATCH.getErrorMessage());
    }
}
