package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @Column(length = 4)
    private String writer;

    @Column(length = 10)
    private String title;

    @Column(length = 200)
    private String contents;
}
