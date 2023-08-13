package practice.oopquerydsl.booklending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.oopquerydsl.booklending.entity.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

}
