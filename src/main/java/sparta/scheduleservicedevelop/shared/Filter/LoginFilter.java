package sparta.scheduleservicedevelop.shared.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletResponse;

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new NotAuthenticatedException();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
