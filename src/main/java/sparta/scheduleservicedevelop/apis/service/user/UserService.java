package sparta.scheduleservicedevelop.apis.service.user;

import sparta.scheduleservicedevelop.entity.User;

public interface UserService {
    User createUser(User user);
    User fetchOneById(Long id);
    void updateUser(Long id, User updateUser);
    void deleteUser(Long id);
    User login(User user);
}
