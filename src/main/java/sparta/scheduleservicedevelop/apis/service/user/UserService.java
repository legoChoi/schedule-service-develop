package sparta.scheduleservicedevelop.apis.service.user;

import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.FetchUserResDto;
import sparta.scheduleservicedevelop.entity.User;

public interface UserService {
    CreateUserResDto createUser(CreateUserReqDto createUserReqDto);
    FetchUserResDto fetchOneById(Long userId);
    void updateUser(Long userId, User updateUser);
    void deleteUser(Long userId);
    User login(User user);
}
