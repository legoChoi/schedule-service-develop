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
        User findUser = getUserByEmail(loginUserReqDto);
        validatePassword(loginUserReqDto, findUser);
        return findUser.getId();
    }

    private User getUserByEmail(LoginUserReqDto loginUserReqDto) {
        return this.userRepository.findByEmail(loginUserReqDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
    }

    private void validatePassword(LoginUserReqDto loginUserReqDto, User findUser) {
        if (!this.passwordEncoder.matches(loginUserReqDto.getPassword(), findUser.getPassword())) {
            throw new UserPasswordMismatchException();
        }
    }
}
