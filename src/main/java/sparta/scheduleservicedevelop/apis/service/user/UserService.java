package sparta.scheduleservicedevelop.apis.service.user;

import sparta.scheduleservicedevelop.entity.User;

public interface UserService {
    User save(User user);
    User findById(Long id);
    void delete(Long id);
    User login(User user);
    void updateUser(Long id, User updateUser);
}