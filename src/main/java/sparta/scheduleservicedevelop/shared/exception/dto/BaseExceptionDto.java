package sparta.scheduleservicedevelop.shared.exception.dto;

import java.time.LocalDateTime;

public class BaseExceptionDto<T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int statusCode;
    private T exception;
}
