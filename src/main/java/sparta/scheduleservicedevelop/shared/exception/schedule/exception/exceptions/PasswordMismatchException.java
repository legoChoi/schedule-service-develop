package sparta.scheduleservicedevelop.shared.exception.schedule.exception.exceptions;


import sparta.scheduleservicedevelop.shared.exception.schedule.exception.ScheduleExceptionMessages;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super(ScheduleExceptionMessages.PASSWORD_MISMATCH.getErrorMessage());
    }
}
