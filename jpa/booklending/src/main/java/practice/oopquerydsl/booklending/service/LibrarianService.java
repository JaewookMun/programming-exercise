package practice.oopquerydsl.booklending.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.oopquerydsl.booklending.entity.Book;
import practice.oopquerydsl.booklending.entity.BookStatus;
import practice.oopquerydsl.booklending.entity.BookUserReservation;
import practice.oopquerydsl.booklending.entity.User;
import practice.oopquerydsl.booklending.repository.LibrarianRepository;
import practice.oopquerydsl.booklending.service.dto.BookInfoDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public BookInfoDto lend(Book book, User user) {
        book.changeStatus(BookStatus.BORROWED, user);

        return BookInfoDto.builder()
                .bookName(book.getName())
                .author(book.getAuthor())
                .dueDate(book.getDueDate())
                .build();
    }

    public BookInfoDto reserve(Book book, User user) {
        book.changeStatus(BookStatus.RESERVED, user);

        // todo: 도서관에 존재하는 책이 모두 대여중인 상태이기 때문에 해당 책에 대해서 예약처리하는 로직
        System.out.println("reserve");
        return BookInfoDto.builder()
                .bookName(book.getName())
                .author(book.getAuthor())
                .available(false)
                .reservedDate(LocalDate.now().plusDays(10))
                .build();
    }

    public BookInfoDto buy(String name, String author, User user) {
        // todo: 도서관에 필요한 책의 구매 요청
        System.out.println();
        return BookInfoDto.builder()

                .build();
    }


    public BookInfoDto findBookWithFastestSchedule(List<Book> lentBooks, User user) throws NoBookException {

        if(lentBooks.isEmpty())
            throw new NoBookException("there is no book for the request");

        Book fastBook = lentBooks.stream()
                .sorted(Comparator
                        .comparing(book -> ((Book) book).getReservationList().size())
                        .thenComparing(book -> ((Book) book).getDueDate()))
                .findAny().get();

        fastBook.getReservationList().add(new BookUserReservation(fastBook, user));

        return BookInfoDto.builder()
                .bookName(fastBook.getName())
                .author(fastBook.getAuthor())
                .isbn(fastBook.getIsbn())
                .build();
    }
}