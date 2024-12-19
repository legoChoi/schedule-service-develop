package sparta.scheduleservicedevelop.shared.exception.auth.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.message.AuthExceptionMessages;

@Getter
public class UnAuthorizedException extends RuntimeException {

    private final int errorCode = AuthExceptionMessages.UN_AUTHORIZED_EXCEPTION.getErrorCode();

    public UnAuthorizedException() {
        super(AuthExceptionMessages.UN_AUTHORIZED_EXCEPTION.getErrorMessage());
    }
}
