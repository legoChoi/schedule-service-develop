package sparta.scheduleservicedevelop.apis.repository.schedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleListJoinResDto;
import sparta.scheduleservicedevelop.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query(value = "SELECT new sparta.scheduleservicedevelop.apis.controller.schedule.dto.response.FetchScheduleListJoinResDto(" +
            "s.title, s.contents, u.userName, COUNT(c), s.createdAt, s.updatedAt) " +
            "FROM Schedule s INNER JOIN s.user u LEFT JOIN s.commentList c GROUP BY s",
    countQuery = "SELECT COUNT(s) FROM Schedule s")
    Page<FetchScheduleListJoinResDto> findScheduleAllCountBy(Pageable pageable);
}
