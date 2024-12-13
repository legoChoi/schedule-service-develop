package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Setter
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
