package sparta.scheduleservicedevelop.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidFieldErrorDto {
    private String field;
    private Object input;
    private String message;
}
