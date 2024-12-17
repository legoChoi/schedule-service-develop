package sparta.scheduleservicedevelop.apis.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduleservicedevelop.apis.controller.auth.dto.request.LoginUserReqDto;
import sparta.scheduleservicedevelop.apis.repository.user.UserRepository;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserNotFoundException;
import sparta.scheduleservicedevelop.shared.exception.user.exception.UserPasswordMismatchException;
import sparta.scheduleservicedevelop.shared.tools.bcrypt.Encoder;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final Encoder passwordEncoder;

    @Override
    public Long login(LoginUserReqDto loginUserReqDto) {
        // 조회
        Optional<User> checkUser = this.userRepository.findByEmail(loginUserReqDto.getEmail());

        // 이메일 검증
        if (checkUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User findUser = checkUser.get();

        // 비밀번호 검증
        if (!this.passwordEncoder.matches(loginUserReqDto.getPassword(), findUser.getPassword())) {
            throw new UserPasswordMismatchException();
        }

        return findUser.getId();
    }
}
