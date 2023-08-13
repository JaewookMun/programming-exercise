package practice.oopquerydsl.booklending.repository;

import practice.oopquerydsl.booklending.entity.Book;
import practice.oopquerydsl.booklending.repository.dto.BookSearchCondition;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> search(BookSearchCondition condition);
}
