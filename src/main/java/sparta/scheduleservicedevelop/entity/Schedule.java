package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @Setter
    @Column(length = 10, nullable = false)
    private String title;

    @Setter
    @Column(length = 200, nullable = false)
    private String contents;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Schedule() {}

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
