package sparta.scheduleservicedevelop.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidExceptionDto {
    private int code;
    private List<ValidFieldErrorDto> fieldErrors;
}


