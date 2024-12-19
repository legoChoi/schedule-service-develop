package sparta.scheduleservicedevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleServiceDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleServiceDevelopApplication.class, args);
	}
}
