package sparta.scheduleservicedevelop.shared.exception.user.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.user.exception.message.UserExceptionMessages;

@Getter
public class UserNotFoundException extends IllegalArgumentException {

    private final int errorCode;

    public UserNotFoundException() {
        super(UserExceptionMessages.USER_NOT_FOUND.getErrorMessage());
        this.errorCode = UserExceptionMessages.USER_NOT_FOUND.getErrorCode();
    }
}
