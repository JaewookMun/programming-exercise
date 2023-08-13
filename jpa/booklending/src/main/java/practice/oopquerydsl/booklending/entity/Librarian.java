package practice.oopquerydsl.booklending.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Librarian {

    @Id @GeneratedValue
    @Column(name = "librarian_id")
    private Long id;
    private String name;

}
