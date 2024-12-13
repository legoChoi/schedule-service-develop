package sparta.scheduleservicedevelop.shared.exception.schedule.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ScheduleExceptionMessages {
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"일정 게시글이 존재하지 않습니다.");

    private final int errorCode;
    private final String errorMessage;
}
