package sparta.scheduleservicedevelop.shared.exception.user.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserExceptionMessages {

    ALREADY_EXISTS_USER_EMAIL(HttpStatus.UNAUTHORIZED.value(), "이미 존재하는 이메일입니다."),
    USER_PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED.value(), "일치하지 않는 비밀번호입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않은 유저입니다.");

    private final int errorCode;
    private final String errorMessage;
}
