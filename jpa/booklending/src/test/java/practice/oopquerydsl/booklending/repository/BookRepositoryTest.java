package practice.oopquerydsl.booklending.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.oopquerydsl.booklending.repository.dto.BookSearchCondition;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void searchTest() {
        BookSearchCondition condition = BookSearchCondition.builder()
                .name("jpa")
                .author("김영한")
                .build();

        System.out.println("condition = " + condition);
        bookRepository.search(condition);
    }
}