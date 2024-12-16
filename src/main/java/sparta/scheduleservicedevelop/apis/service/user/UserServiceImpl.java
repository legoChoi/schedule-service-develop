package sparta.scheduleservicedevelop.apis.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;
import sparta.scheduleservicedevelop.shared.tools.bcrypt.Encoder;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encoder passwordEncoder;

    @Override
    public User createUser(User user) {
        // 중복 이메일 검증
        Optional<User> checkUser = this.userRepository.findByEmail(user.getEmail());

        if (checkUser.isPresent()) {
            throw new AlreadyExistsUserEmailException();
        }

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User createdUser = new User(
                user.getUserName(),
                encodedPassword,
                user.getEmail()
        );

        // 생성
        return this.userRepository.save(createdUser);
    }

    @Override
    public User fetchOneById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser(Long id) {
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
        if (!this.passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
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
