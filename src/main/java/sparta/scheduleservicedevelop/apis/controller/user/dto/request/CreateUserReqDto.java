package sparta.scheduleservicedevelop.apis.controller.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserReqDto {

    @NotBlank @Size(min = 1, max = 4)
    private String userName;

    @NotBlank @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank @Size(min = 6, max = 15)
    private String password;
}
