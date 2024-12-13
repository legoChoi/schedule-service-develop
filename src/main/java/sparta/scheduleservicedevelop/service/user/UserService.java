package sparta.scheduleservicedevelop.service.user;

import sparta.scheduleservicedevelop.entity.User;

public interface UserService {
    User save(User user);
    User findById(Long id);
    void delete(Long id);
}
