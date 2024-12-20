package sparta.scheduleservicedevelop.shared.exception.schedule.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.schedule.exception.message.ScheduleExceptionMessages;

@Getter
public class ScheduleNotFoundException extends IllegalArgumentException {

    private final int errorCode = ScheduleExceptionMessages.SCHEDULE_NOT_FOUND.getErrorCode();

    public ScheduleNotFoundException() {
        super(ScheduleExceptionMessages.SCHEDULE_NOT_FOUND.getErrorMessage());
    }
}
