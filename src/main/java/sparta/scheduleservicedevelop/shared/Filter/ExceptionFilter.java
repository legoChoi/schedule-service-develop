package sparta.scheduleservicedevelop.shared.Filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.NotAuthenticatedException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;

import java.io.IOException;

@Slf4j
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("REQUEST [{}][{}][{}]", httpMethod, requestURI, request.getDispatcherType());

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (NotAuthenticatedException e) {
            log.info("HANDLE EXCEPTION [{}][{}][{}]", httpMethod, requestURI, request.getDispatcherType());
            response.setStatus(e.getErrorCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(build(e));
        }
    }

    private String build(NotAuthenticatedException e) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                new ExceptionDto(
                        e.getErrorCode(), e.getMessage())
        );
    }
}
