package practice.oopquerydsl.booklending.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookUserReservation {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservedDate;

    public BookUserReservation(Book book, User user) {
        this(book, user, LocalDateTime.now());
    }

    public BookUserReservation(Book book, User user, LocalDateTime reservedDate) {
        this.book = book;
        this.user = user;
        this.reservedDate = reservedDate;
    }
}
