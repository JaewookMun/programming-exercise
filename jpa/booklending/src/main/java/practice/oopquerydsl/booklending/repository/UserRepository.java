package practice.oopquerydsl.booklending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.oopquerydsl.booklending.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCode(String code);
}
