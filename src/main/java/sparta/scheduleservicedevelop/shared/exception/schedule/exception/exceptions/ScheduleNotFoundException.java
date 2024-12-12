package sparta.scheduleservicedevelop.shared.exception.schedule.exception.exceptions;


import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleExceptionMessages;

public class ScheduleNotFoundException extends IllegalArgumentException {
    public ScheduleNotFoundException() {
        super(ScheduleExceptionMessages.SCHEDULE_NOT_FOUND.getErrorMessage());
    }
}
