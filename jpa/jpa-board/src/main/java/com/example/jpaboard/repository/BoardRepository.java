package com.example.jpaboard.repository;

import com.example.jpaboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleLike(String title);

    @EntityGraph(attributePaths = {"member"})
    @Override
    List<Board> findAll();

    @EntityGraph(attributePaths = {"member"})
    @Override
    Page<Board> findAll(Pageable pageable);

    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id = :id")
    int increaseViewCount(@Param("id") Long id);
}
