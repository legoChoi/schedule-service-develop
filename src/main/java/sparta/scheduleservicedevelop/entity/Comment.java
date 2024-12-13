package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Entity
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 100, nullable = false)
    private String contents;
}
