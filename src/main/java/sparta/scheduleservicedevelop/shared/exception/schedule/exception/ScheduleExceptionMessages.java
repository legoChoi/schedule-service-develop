package sparta.scheduleservicedevelop.shared.exception.schedule.exception;

import lombok.Getter;

@Getter
public enum ScheduleExceptionMessages {
    SCHEDULE_NOT_FOUND("일정 게시글이 존재하지 않습니다.");

    private final String errorMessage;

    ScheduleExceptionMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
