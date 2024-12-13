package sparta.scheduleservicedevelop.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduleservicedevelop.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.controller.user.dto.request.LoginUserReqDto;
import sparta.scheduleservicedevelop.controller.user.dto.request.UpdateUserReqDto;
import sparta.scheduleservicedevelop.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.controller.user.dto.response.FetchUserResDto;
import sparta.scheduleservicedevelop.entity.User;
import sparta.scheduleservicedevelop.service.user.UserService;
import sparta.scheduleservicedevelop.shared.session.SessionNames;

@Slf4j
@RestController
@RequestMapping("/apis/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResDto> createUser(
            @RequestBody CreateUserReqDto createUserReqDto
    ) {
        User user = new User(
                createUserReqDto.getUserName(),
                createUserReqDto.getPassword(),
                createUserReqDto.getEmail()
        );

        User savedUser = this.userService.save(user);

        CreateUserResDto data = new CreateUserResDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FetchUserResDto> fetchUser(
            @PathVariable("userId") Long userId
    ) {
        User findUser = this.userService.findById(userId);

        FetchUserResDto data = new FetchUserResDto(
                findUser.getId(),
                findUser.getUserName(),
                findUser.getEmail(),
                findUser.getCreatedAt(),
                findUser.getUpdatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("userId") Long userId
    ) {
        this.userService.delete(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody UpdateUserReqDto updateUserReqDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(SessionNames.LOGIN_USER.getTag());

        loginUser.setUserName(updateUserReqDto.getUserName());

        this.userService.updateUser(loginUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginUserReqDto loginUserReqDto,
            HttpServletRequest request
    ) {
        User user = new User(
                loginUserReqDto.getPassword(),
                loginUserReqDto.getEmail()
        );

        User login = this.userService.login(user);

        request.getSession().setAttribute(SessionNames.LOGIN_USER.getTag(), login);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
