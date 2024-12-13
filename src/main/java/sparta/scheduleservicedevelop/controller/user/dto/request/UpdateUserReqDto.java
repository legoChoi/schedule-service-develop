package sparta.scheduleservicedevelop.controller.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserReqDto {

    @NotBlank @Size(min = 1, max = 4)
    private String userName;
}
