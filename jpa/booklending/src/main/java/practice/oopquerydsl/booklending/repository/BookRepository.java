package practice.oopquerydsl.booklending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.oopquerydsl.booklending.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
