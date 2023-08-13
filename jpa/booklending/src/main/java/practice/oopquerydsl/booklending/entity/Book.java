package practice.oopquerydsl.booklending.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;
    private String name;
    private String author;
    @Column(unique = true)
    private String isbn;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;
    private LocalDate lentDate;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookUserReservation> reservationList = new ArrayList<>();

    @Builder
    public Book(String name, String author, String isbn, BookStatus status) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public void changeStatus(BookStatus status, User user) {
//        Assert.isTrue(BookStatus.AVAILABLE.equals(this.status), "for lend this book, BookStatus should be available");
        Assert.notNull(status, "BookStatus should be not null");

        this.status = status;
        dueDate = LocalDate.now().plusDays(14);
        this.user = user;
    }
}
