package sparta.scheduleservicedevelop.apis.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;
import sparta.scheduleservicedevelop.shared.tools.bcrypt.Encoder;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encoder passwordEncoder;

    @Override
    @Transactional
    public CreateUserResDto createUser(CreateUserReqDto createUserReqDto) {
        // 중복 이메일 검증
        Optional<User> checkUser = this.userRepository.findByEmail(createUserReqDto.getEmail());

        if (checkUser.isPresent()) {
            throw new AlreadyExistsUserEmailException();
        }

        String rawPassword = createUserReqDto.getPassword();
        String encodedPassword = this.passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .userName(createUserReqDto.getUserName())
                .password(encodedPassword)
                .email(createUserReqDto.getEmail())
                .build();

        // 생성
        User savedUser = this.userRepository.save(user);

        return CreateUserResDto.from(savedUser);
    }

    @Override
    public User fetchOneById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
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
    @Transactional
    public void updateUser(Long id, User updateUser) {
        User findUser = this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        findUser.setUserName(updateUser.getUserName());
    }
}
