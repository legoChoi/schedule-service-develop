package sparta.scheduleservicedevelop.apis.service.user;

import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.UpdateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.FetchUserResDto;

public interface UserService {
    CreateUserResDto createUser(CreateUserReqDto createUserReqDto);
    FetchUserResDto fetchOneById(Long userId);
    void updateUser(Long userId, UpdateUserReqDto updateUserReqDto);
    void deleteUser(Long userId);
}
