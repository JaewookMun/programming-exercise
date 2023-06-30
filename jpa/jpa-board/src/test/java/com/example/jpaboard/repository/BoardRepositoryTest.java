package com.example.jpaboard.repository;

import com.example.jpaboard.domain.Board;
import com.example.jpaboard.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;


    @Test
    public void 게시글_추가() {
        //given
        Member member = new Member("james", "1234");
        memberRepository.save(member);

        String title = "Spring Data JPA in Spring boot";
        String content = "it's very nice";
        Board board = new Board(title, content, member);
        boardRepository.save(board);

        em.flush();
        em.clear();
        System.out.println("------------------------------------------");

        //when
        Board findOne = boardRepository.findByTitleLike("%JPA%").get(0);
        Member writer = findOne.getMember();

        //then
        assertThat(findOne.getTitle()).isEqualTo(title);
        assertThat(findOne.getContent()).isEqualTo(content);

        assertThat(writer.getName()).isEqualTo(member.getName());
        assertThat(writer.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    public void 게시물_조회() {
        // given
        Member member = new Member("tester", "password");
        memberRepository.save(member);

        String title = "title for JPA";
        String content = "create code faster!!!";

        for(int i=0; i<10; i++) {
            Board board = new Board(title + i, content + i, member);
            boardRepository.save(board);
        }

        em.flush();
        em.clear();

        // when
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "title"));
        Page<Board> currentPage = boardRepository.findAll(pageRequest);

        assertThat(currentPage.getNumber()).isEqualTo(page);
        assertThat(currentPage.getSize()).isEqualTo(size);
        assertThat(currentPage.getTotalElements()).isEqualTo(10);
        assertThat(currentPage.isFirst());
        assertThat(currentPage.hasNext());

        for (Board board : currentPage.getContent()) {
            System.out.println(board);
        }
    }
}