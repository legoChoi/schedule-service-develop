package sparta.scheduleservicedevelop.shared.exception.auth.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthExceptionMessages {
    NOT_AUTHENTICATED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");

    private final int errorCode;
    private final String errorMessage;
}