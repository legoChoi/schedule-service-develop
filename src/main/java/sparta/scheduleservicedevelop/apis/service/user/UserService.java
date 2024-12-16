package sparta.scheduleservicedevelop.apis.service.user;

import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.entity.User;

public interface UserService {
    CreateUserResDto createUser(CreateUserReqDto createUserReqDto);
    User fetchOneById(Long id);
    void updateUser(Long id, User updateUser);
    void deleteUser(Long id);
    User login(User user);
}
