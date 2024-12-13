package sparta.scheduleservicedevelop.shared.exception.user.exception.exceptions;

import sparta.scheduleservicedevelop.shared.exception.user.exception.UserExceptionMessages;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super(UserExceptionMessages.USER_NOT_FOUND.getErrorMessage());
    }
}
