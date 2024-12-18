package sparta.scheduleservicedevelop.apis.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.scheduleservicedevelop.apis.controller.auth.dto.request.LoginUserReqDto;
import sparta.scheduleservicedevelop.apis.service.auth.AuthService;
import sparta.scheduleservicedevelop.shared.session.SessionUserUtils;

@RestController
@RequestMapping("/apis/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginUserReqDto loginUserReqDto,
            HttpServletRequest request
    ) {
        Long userId = this.authService.login(loginUserReqDto);

        SessionUserUtils.setId(userId, request);

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
