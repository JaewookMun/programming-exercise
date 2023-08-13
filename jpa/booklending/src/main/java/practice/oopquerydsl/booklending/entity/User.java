package practice.oopquerydsl.booklending.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private UserType type;

    /** if user is member of library, he has this */
    @Column(unique = true)
    private String code;

    public User(String name, String mobile, UserType type) {
        this.name = name;
        this.mobile = mobile;
        this.type = type;
    }
}
