package sparta.scheduleservicedevelop.shared.exception.user.exception;

import lombok.Getter;

@Getter
public enum UserExceptionMessages {
    ALREADY_EXISTS_USER_EMAIL("이미 존재하는 이메일입니다."),
    USER_NOT_FOUND("존재하지 않은 유저입니다."),
    USER_PASSWORD_MISMATCH("일치하지 않는 비밀번호입니다.");

    private final String errorMessage;

    UserExceptionMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
