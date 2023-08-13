package practice.oopquerydsl.booklending.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import practice.oopquerydsl.booklending.entity.Book;
import practice.oopquerydsl.booklending.entity.BookStatus;
import practice.oopquerydsl.booklending.entity.User;
import practice.oopquerydsl.booklending.entity.UserType;
import practice.oopquerydsl.booklending.repository.BookRepository;
import practice.oopquerydsl.booklending.repository.UserRepository;
import practice.oopquerydsl.booklending.repository.dto.BookSearchCondition;
import practice.oopquerydsl.booklending.service.dto.BookInfoDto;
import practice.oopquerydsl.booklending.web.dto.BookRequestDto;
import practice.oopquerydsl.booklending.web.dto.UserRequestDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * todo : User 도메인이 수신하게되는 메시지이지만 다른 도메인과 연관관계를 맺게된다.
 * todo : 때문에 service 수준에서 해당 메시지에 대한 처리.
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final LibrarianService librarianService;


    @Transactional
    public BookInfoDto borrow(BookRequestDto bookRequest, UserRequestDto userDto) {

        User user = getUserBy(userDto);

        BookSearchCondition searchCondition = BookSearchCondition.of(bookRequest);
        List<Book> bookList = bookRepository.search(searchCondition);
        List<Book> lentBooks = new ArrayList<>();

        Iterator<Book> itr = bookList.iterator();
        while (itr.hasNext()) {
            Book book = itr.next();

            if(book.getStatus().equals(BookStatus.AVAILABLE))
                return librarianService.lend(book, user);

            if(book.getStatus().equals(BookStatus.BORROWED) || book.getStatus().equals(BookStatus.RESERVED))
                lentBooks.add(book);
        }

        BookInfoDto bookInfo;
        try {
            bookInfo = librarianService.findBookWithFastestSchedule(lentBooks, user);
        } catch (NoBookException e) {
            log.warn("The library doesn't have the book, {}, so they have to but it", bookRequest.getBookName());
            bookInfo = BookInfoDto.builder()
                    .bookName(bookRequest.getBookName())
                    .author(bookRequest.getAuthor())
                    .available(false)
                    .build();
        }

        return bookInfo;
    }

    /**
     * {@link UserRequestDto}
     * @param userDto
     * @return
     */
    private User getUserBy(UserRequestDto userDto) {

        User user;

        if(StringUtils.hasText(userDto.getCode()))
            user = userRepository.findByCode(userDto.getCode()).get();
        else {
            user = new User(userDto.getUserName(), userDto.getMobile(), UserType.GUEST);
            userRepository.save(user);
        }

        return user;
    }
}
