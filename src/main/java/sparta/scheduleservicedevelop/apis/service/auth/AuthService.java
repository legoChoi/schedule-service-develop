package sparta.scheduleservicedevelop.apis.service.auth;

import sparta.scheduleservicedevelop.apis.controller.auth.dto.request.LoginUserReqDto;

public interface AuthService {
    Long login(LoginUserReqDto loginUserReqDto);
}
