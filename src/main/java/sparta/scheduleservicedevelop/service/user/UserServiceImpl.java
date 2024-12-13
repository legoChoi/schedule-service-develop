package sparta.scheduleservicedevelop.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.repository.user.UserRepository;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        // 중복 이메일 검증
        Optional<User> checkUser = this.userRepository.findByEmail(user.getEmail());

        if (checkUser.isPresent()) {
            throw new AlreadyExistsUserEmailException();
        }

        // 생성
        return this.userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
    }

    @Override
    public User login(User user) {
        // 조회
        Optional<User> checkUser = this.userRepository.findByEmail(user.getEmail());

        // 이메일 검증
        if (checkUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User findUser = checkUser.get();

        // 비밀번호 검증
        if (!user.getPassword().equals(findUser.getPassword())) {
            throw new UserPasswordMismatchException();
        }

        return findUser;
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        User findUser = this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        findUser.setUserName(updateUser.getUserName());
    }
}
