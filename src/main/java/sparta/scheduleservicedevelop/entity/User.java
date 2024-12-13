package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 4)
    private String userName;

    @Column(length = 100)
    private String password;

    @Column(name = "user_email", unique = true, length = 50)
    private String email;

    protected User() {}

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
