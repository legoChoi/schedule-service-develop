package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Setter
    @Column(length = 10, nullable = false)
    private String userName;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    @Builder
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
