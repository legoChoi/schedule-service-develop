package sparta.scheduleservicedevelop.shared.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sparta.scheduleservicedevelop.shared.Filter.ExceptionFilter;
import sparta.scheduleservicedevelop.shared.Filter.LoginCheckFilter;

/**
 * 서블릿 필터 사용을 위한 Config
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> exceptionFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ExceptionFilter());
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.addUrlPatterns("/apis/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/apis/*");

        return filterRegistrationBean;
    }
}
