package sparta.scheduleservicedevelop.apis.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.CreateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.request.UpdateUserReqDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.CreateUserResDto;
import sparta.scheduleservicedevelop.apis.controller.user.dto.response.FetchUserResDto;
import sparta.scheduleservicedevelop.apis.service.user.UserService;
import sparta.scheduleservicedevelop.shared.session.SessionUserUtils;

@Slf4j
@RestController
@RequestMapping("/apis/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResDto> createUser(
            @Valid @RequestBody CreateUserReqDto createUserReqDto
    ) {
        CreateUserResDto data = this.userService.createUser(createUserReqDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<FetchUserResDto> fetchUser(
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        FetchUserResDto data = this.userService.fetchOneById(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        this.userService.deleteUser(userId);

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody UpdateUserReqDto updateUserReqDto,
            HttpServletRequest request
    ) {
        Long userId = SessionUserUtils.getId(request);
        this.userService.updateUser(userId, updateUserReqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
