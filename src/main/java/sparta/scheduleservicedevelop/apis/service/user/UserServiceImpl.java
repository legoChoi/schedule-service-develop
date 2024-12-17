package sparta.scheduleservicedevelop.apis.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.UpdateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.FetchUserResDto;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.user.exception.AlreadyExistsUserEmailException;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
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
    public FetchUserResDto fetchOneById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return FetchUserResDto.from(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        this.userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UpdateUserReqDto updateUserReqDto) {
        User findUser = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        findUser.setUserName(updateUserReqDto.getUserName());
    }
}
