package sparta.scheduleservicedevelop.shared.exception.auth.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.message.AuthExceptionMessages;

@Getter
public class NotAuthenticatedException extends RuntimeException {

  private final int errorCode = AuthExceptionMessages.NOT_AUTHENTICATED_EXCEPTION.getErrorCode();

  public NotAuthenticatedException() {
    super(AuthExceptionMessages.NOT_AUTHENTICATED_EXCEPTION.getErrorMessage());
  }
}
