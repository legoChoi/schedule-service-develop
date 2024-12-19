package sparta.scheduleservicedevelop.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ExceptionDto {
    private LocalDateTime timestamp;
    private String exception;
    private int code;
    private String message;
}
