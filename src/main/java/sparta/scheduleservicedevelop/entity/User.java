package sparta.scheduleservicedevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sparta.scheduleservicedevelop.shared.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Setter
    @Column(length = 4)
    private String userName;

    @Column(length = 100)
    private String password;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    protected User() {}

    public User(String userName) {
        this.userName = userName;
    }

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
