package sparta.scheduleservicedevelop.shared.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;
import sparta.scheduleservicedevelop.shared.session.SessionNames;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private final String[] uriWhiteList = {
            "/apis/users", // 유저 가입
            "/apis/users/login", // 로그인
            "/apis/users/logout", // 로그아웃
    };

    private final String[] methodWhiteList = {
            "PATCH",
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        if (!PatternMatchUtils.simpleMatch(uriWhiteList, requestURI)
                || PatternMatchUtils.simpleMatch(methodWhiteList, httpMethod)) {
            validateSession(request);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void validateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionNames.LOGIN_USER.getTag()) == null) {
            throw new NotAuthenticatedException();
        }
    }
}