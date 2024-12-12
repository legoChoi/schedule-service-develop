package sparta.scheduleservicedevelop.shared.exception.schedule.exception;


import lombok.Getter;

@Getter
public enum ScheduleExceptionMessages {
    PASSWORD_MISMATCH("일정 비밀번호가 일치하지 않습니다."),
    SCHEDULE_NOT_FOUND("일정 게시글이 존재하지 않습니다.");

    private final String errorMessage;

    ScheduleExceptionMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
