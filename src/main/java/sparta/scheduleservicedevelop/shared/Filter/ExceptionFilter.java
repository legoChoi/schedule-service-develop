package sparta.scheduleservicedevelop.shared.Filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import sparta.scheduleservicedevelop.shared.exception.auth.exception.UserNotLoggedInException;
import sparta.scheduleservicedevelop.shared.exception.dto.ExceptionDto;

import java.io.IOException;
import java.time.LocalDateTime;

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
        } catch (UserNotLoggedInException e) {
            log.info("HANDLE EXCEPTION [{}][{}][{}]", httpMethod, requestURI, e.getClass().getSimpleName());
            response.setStatus(e.getErrorCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(build(e));
        }
    }

    private String build(UserNotLoggedInException e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO 8601 형식 사용

        // JSON 문자열 생성
        return objectMapper.writeValueAsString(
                new ExceptionDto(
                        LocalDateTime.now(),
                        e.getClass().getSimpleName(),
                        e.getErrorCode(),
                        e.getMessage()
                )
        );
    }
}
