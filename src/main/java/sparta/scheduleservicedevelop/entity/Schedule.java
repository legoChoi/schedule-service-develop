package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @Column(length = 10, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    // method
    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
