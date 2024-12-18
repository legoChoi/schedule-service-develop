package sparta.scheduleservicedevelop.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidExceptionDto {
    private LocalDateTime timestamp;
    private String exception;
    private int code;
    private List<ValidFieldErrorDto> fieldErrors;
}


