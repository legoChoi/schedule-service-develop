package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(length = 4)
    private String writer;

    @Column(length = 10)
    private String title;

    @Column(length = 200)
    private String contents;

    protected Schedule() {
    }

    public Schedule(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
