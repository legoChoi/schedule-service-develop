package sparta.scheduleservicedevelop.shared.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;

import java.io.IOException;

public class ExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (NotAuthenticatedException e) {
            response.setStatus(e.getErrorCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new ExceptionDto(
                                    e.getErrorCode(), e.getMessage())
                    )
            );
        }
    }
}
