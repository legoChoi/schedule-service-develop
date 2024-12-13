package sparta.scheduleservicedevelop.controller.user.dto.request;

import lombok.Getter;

@Getter
public class CreateUserReqDto {
    private String userName;
    private String password;
    private String email;
}
